package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FunRestController {

    // inject properties for: coach.name and team.name
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // Expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello(){
        return "HelloWorld! Time on server is " + LocalDateTime.now();
    }

    // Expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout(){
        return "Run 5 miles!";
    }

    // Expose a new endpoint for "teaminfo"
    @GetMapping("/teaminfo")
    public String getTeamInfo(){
        return "Coach: " + coachName + ", Team Name: " + teamName;
    }

}
