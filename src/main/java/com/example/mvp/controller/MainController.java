package com.example.mvp.controller;

import com.example.mvp.domain.Message;
import com.example.mvp.domain.User;
import com.example.mvp.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String testik(
            @RequestParam(name = "name", required = false, defaultValue = "Dima")String name,
            Map<String,Object> model)
    {
        model.put("some",name);
        return "testik";
    }
    @GetMapping("/main") // Работа с URL
    public String main(@RequestParam(required = false, defaultValue = "") String filter,Model model)
    {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter!=null && !filter.isEmpty()) // Проверка на то фильтр
        {
            //messages = messageRepo.findByText(filter); // ищем по тексту
            messages = messageRepo.findByTag(filter);//ищем по тегу
        }
        else
        {
            messages=messageRepo.findAll();//всё ищем
        }
        model.addAttribute("messages",messages);
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping("/main") //Работа с формами и тд. Этот мепинг для сохрание
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Map<String ,Object>model)
    {
        //Сохранили text и tag
        Message message = new Message(text, tag,user);
        messageRepo.save(message);



        //Взяли из репозитория положили в модель и отдали Main
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages",messages);
        return "main";
    }


}
