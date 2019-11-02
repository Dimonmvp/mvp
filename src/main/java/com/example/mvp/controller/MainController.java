package com.example.mvp.controller;

import com.example.mvp.domain.Message;
import com.example.mvp.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller//метка контроллера
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")//Maping в адресной строке,есле /greeting то выполняеться этот метод
    public String greeting(Map<String,Object> model) {
        //model.addAttribute("name","name");

        return "greeting"; // Возвращае страницу greeting
    }

    @GetMapping("/testik")
    public String testik(@RequestParam(name = "name", required = false, defaultValue = "Dima")String name,Map<String,Object> model)
    {
        model.put("some",name);
        return "testik";
    }
    @GetMapping("/main") // Работа с URL
    public String main(Map<String ,Object>model)
    {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages",messages);
        return "main";
    }

    @PostMapping("/main") //Работа с формами и тд. Этот мепинг для сохрание
    public String add(@RequestParam String text,@RequestParam String tag, Map<String ,Object>model)
    {
        //Сохранили text и tag
        Message message = new Message(text, tag);
        messageRepo.save(message);



        //Взяли из репозитория положили в модель и отдали Main
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages",messages);
        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, //Spring передает сюда тег/текст который получаем с поля filter в main
                         Map<String ,Object>model)
    {
        Iterable<Message> messages;//итерабле
        if (filter!=null && !filter.isEmpty()) // Проверка на то фильтр
        {
            //messages = messageRepo.findByText(filter); // ищем по тексту
             messages = messageRepo.findByTag(filter);//ищем по тегу
        }
        else
        {
            messages=messageRepo.findAll();//всё ищем
        }

        model.put("messages",messages);//закидываем в модель и передаем в main
        return "main";
    }

}
