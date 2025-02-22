package com.example.Greeting.App.controller;

import com.example.Greeting.App.Service.GreetingService;
import com.example.Greeting.App.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        GreetingService obj=new GreetingService();
        Map<String ,String > response=new HashMap<>();

        response.put("response is ", obj.greetingWithName(firstName,lastName));
        return response ;

    }

    //UC-04 Ability for the Greeting App to save the Greeting Message in the Repository

    @PostMapping("/save")
    public Greeting saveGreeting(@RequestParam String message) {
        return greetingService.saveGreeting(message);
    }
    @GetMapping("/personalized")
    public String getPersonalizedGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName){
        return greetingService.getPersonalizedGreetings(firstName, lastName);
    }

    //UC-05 Ability for the Greeting App to find a Greeting Message by Id in the Repository

    @GetMapping("/find/{id}")
    public Greeting findGreetingById(@PathVariable Long id){
        return greetingService.findGreetingById(id);
    }

    //UC-06 Ability for the Greeting App to List all the Greeting Messages in the Repository

    @GetMapping("/all")
    public List<Greeting> getAllGreetings(){
        return greetingService.getAllGreetings();
    }

    // UC-07 Ability for the Greeting App to Edit a Greeting Message in the Repository
    @PutMapping("/update/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestParam String message) {
        return greetingService.updateGreeting(id, message);
    }

    // UC-08 Ability for the Greeting App to Delete a Greeting Message from the Repository
    @DeleteMapping("/delete/{id}")
    public String deleteGreetingById(@PathVariable Long id) {
        return greetingService.deleteGreetingById(id);
    }
}
