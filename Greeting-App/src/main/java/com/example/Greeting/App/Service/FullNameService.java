package com.example.Greeting.App.Service;

public class FullNameService {
    public String greetingWithName(String firstName,String lastName){
        if(firstName !=null && lastName!=null){
            return firstName+" Hello World "+lastName;
        }
        else if(lastName!=null){
            return "Hello World "+lastName;
        }
        else if(firstName!=null){
            return firstName+" Hello World ";
        }
        else{
            return "Hello World";
        }
    }
}
