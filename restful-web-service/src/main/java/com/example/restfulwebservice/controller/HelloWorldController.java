package com.example.restfulwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping(method=RequestMethod.GET, path="/hello-korea")
    public String helloKorea() {
        // Legacy
        return "Hello Korea";
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
