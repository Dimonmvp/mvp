package com.example.mvp.repos;

import com.example.mvp.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long>
{
    List<Message> findByText(String text);
    List<Message> findByTag(String tag);
}
