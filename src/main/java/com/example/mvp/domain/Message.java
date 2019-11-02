package com.example.mvp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Дает знать спрингу, что это сущность которуую нужно сохранять в БД
public class Message {
    @Id//ID
    @GeneratedValue(strategy=GenerationType.AUTO) // Spring автоматически генерирует ID
    private Integer id;

    private String text;
    private String tag;


    public Message() {
    }

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
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
}
