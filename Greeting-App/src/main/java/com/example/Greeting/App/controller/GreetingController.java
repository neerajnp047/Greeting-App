package com.example.Greeting.App.controller;

import com.example.Greeting.App.Service.FullNameService;
import com.example.Greeting.App.Service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    // UC-01 Test using curl
    @GetMapping
    public String getGreeting() {
        return"{\"message\":\"Hello, GET request received!\"}";
    }
    @PostMapping
    public String postGreeting() {
        return"{\"message\":\"Hello, POST request received!\"}";
    }
    @PutMapping
    public String putGreeting() {
        return"{\"message\":\"Hello, PUT request received!\"}";
    }
    @DeleteMapping
    public String deleteGreeting(){
        return"{\"message\":\"Hello, DELETE request received!\"}";
    }

    @GetMapping("/combine")
    public Map<String,String> map()
    {
      return  Map.of(
                "first",getGreeting(),
                "second",putGreeting(),
                "third",postGreeting(),
                "forth",deleteGreeting()
        );
    }
    // UC-02 Services Layer to get Simple Greeting
    @Autowired
    private GreetingService greetingService;
    @GetMapping("/service")
    public String getServiceGreeting() {
        return greetingService.getSimpleGreeting();
    }

    // uc3 greeting message with either firstname,lastname or full name
    @GetMapping("/greetingname")
    public Map<String ,String> greetingMethod(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName){
        FullNameService obj=new FullNameService();
        Map<String ,String > response=new HashMap<>();

        response.put("response is ", obj.greetingWithName(firstName,lastName));
        return response ;


    }
}
