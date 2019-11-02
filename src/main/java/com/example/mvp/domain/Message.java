package com.example.mvp.domain;

import javax.persistence.*;

@Entity // Дает знать спрингу, что это сущность которуую нужно сохранять в БД
public class Message {
    @Id//ID
    @GeneratedValue(strategy=GenerationType.AUTO) // Spring автоматически генерирует ID
    private Integer id;

    private String text;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)//один автор много сообщений
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {
    }

    public Message(String text, String tag,User user) {
        this.text = text;
        this.tag = tag;
        this.author=user;
    }

    public String getAuthorName()
    {
        return author!=null ? author.getUsername() : "<none>";
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
