package com.example.Greeting.App.Service;

import com.example.Greeting.App.model.Greeting;
import com.example.Greeting.App.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    //UC-02
    @Autowired
    private GreetingRepository greetingRepository;

    public String getSimpleGreeting() {
        return "Hello World";
    }

    //UC-03

    public String greetingWithName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return firstName + " Hello World " + lastName;
        } else if (lastName != null) {
            return "Hello World " + lastName;
        } else if (firstName != null) {
            return firstName + " Hello World ";
        } else {
            return "Hello World";
        }
    }

    //UC-04

    public Greeting saveGreeting(String message) {
        Greeting greeting = new Greeting(message);
        return greetingRepository.save(greeting);
    }

    public String getPersonalizedGreetings(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World";
        }

        saveGreeting(message);
        return "{\"message\": \"" + message + "\"}";
    }


    public Greeting findGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geeting not found with id: " + id));
    }


    //UC-06

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
}




