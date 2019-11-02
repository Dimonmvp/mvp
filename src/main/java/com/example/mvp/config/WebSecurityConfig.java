package com.example.mvp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) /*Передает на вход объект */throws Exception {
        http
                .authorizeRequests()//Авторизация
                    .antMatchers("/","/registration" ).permitAll() // Даем полный доступ
                    .anyRequest().authenticated()//требуем авторизацию
                .and()
                    .formLogin()//логин
                    .loginPage("/login")//логин находиться в таком мепинге
                    .permitAll()//разрешаем пользоваться всем
                .and()
                    .logout()//логаут
                    .permitAll();//пользоваться всем
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)/*нужно для того чтобы менеджер входил в БД */
                .passwordEncoder(NoOpPasswordEncoder.getInstance())/*Шифрует пароли*/
                .usersByUsernameQuery("select username,password,active from usr where username=?")/*Система находит пользователя по его имени*/
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?"); /*Помогает получить спрингу список пользователей с роля*/
    }
}