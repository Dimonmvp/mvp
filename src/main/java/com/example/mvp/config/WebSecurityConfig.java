package com.example.mvp.config;

import com.example.mvp.sevice.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSevice userSevice;
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
        auth.userDetailsService(userSevice)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());/*Шифрует пароли*/

    }
}