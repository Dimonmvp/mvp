package com.example.mvp.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER /*Определяет как будут подгружаться данные*/ )// Помогает не создавать дополнительную таблицу для Enum  а делает само
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id")/*Позволяет создать таблицу которая будет соединяться с текущей при помощи user_id */)
    //описает поле которое будет храниться в отдельной таблице
    @Enumerated(EnumType.STRING) // хотим enum хранить в виде строки
    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
