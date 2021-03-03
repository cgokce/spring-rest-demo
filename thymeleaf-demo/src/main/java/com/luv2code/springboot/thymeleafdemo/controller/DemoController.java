package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // generate a mapping for "/hello"
    @GetMapping("/hello")
    public String sayHello(Model theModel){

        theModel.addAttribute("theDate", new java.util.Date());

        // we have pom dependency ... spring Boot will auto configure to use thymeleaf
        // will look for ... src/main/resources/templates/helloworld.html
        return "helloworld";
    }

}
