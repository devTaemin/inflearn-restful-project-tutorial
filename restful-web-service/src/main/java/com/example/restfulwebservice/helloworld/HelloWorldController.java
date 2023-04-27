package com.example.restfulwebservice.helloworld;

import com.example.restfulwebservice.helloworld.HelloWorldBean;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanName(@PathVariable(value = "name") String name) { return new HelloWorldBean(String.format("Hello World, %s", name));}
}
