package com.example.mvp.domain.util;

import com.example.mvp.domain.User;

public abstract class MessageHelper
{
    public static String getAuthorName(User author)
    {
        return author!=null ? author.getUsername():"<none>";
    }
}
