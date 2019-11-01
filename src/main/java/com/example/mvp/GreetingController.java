package com.example.mvp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller//метка контроллера
public class GreetingController {
    @GetMapping("/greeting")//Maping в адресной строке,есле /greeting то выполняеться этот метод
    public String greeting(
            @RequestParam(name="name", // связываеться имя переменной "name" и переменная name которая передаеться в String name
                    required=false, // не обязательное значение переменной
                    defaultValue="World") String name, // Дефолтное значение и куда записываться
                    Map<String,Object> model
    ) {
        //model.addAttribute("name","name");
        model.put("name", name); // В name для greeting.mustache передаеться значение name(String)
        return "greeting"; // Возвращае страницу greeting
    }
//    @GetMapping//не указываеться, потому что сразу отображаеться при входе на localhost
//    public String main(Map<String,Object> model)
//    {
//        model.put("some","Welcome to my site");
//        return "main";
//    }
    //    @GetMapping("/testik")
//    public String testik(
//            @RequestParam(name="name", // связываеться имя переменной "name" и переменная name которая передаеться в String name
//                    required=false, // не обязательное значение переменной
//                    defaultValue="World") String name, // Дефолтное значение и куда записываться
//            Map<String,Object> model
//    ) {
//        //model.addAttribute("name","name");
//        model.put("name", name); // В name для greeting.mustache передаеться значение name(String)
//        return "testik"; // Возвращае страницу greeting
//    }
    @GetMapping
    public String testik(@RequestParam(name = "name", required = false, defaultValue = "Dima")String name,Map<String,Object> model)
    {
        model.put("some",name);
        return "testik";
    }
}
