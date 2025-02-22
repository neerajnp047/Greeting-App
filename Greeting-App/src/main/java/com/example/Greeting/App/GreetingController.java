package com.example.Greeting.App;

import org.springframework.web.bind.annotation.*;
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
}
