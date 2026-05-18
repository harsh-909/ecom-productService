package com.ecom.productService.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class TestController {


    @GetMapping("/say/{n}")
    public String sayHello(@PathVariable int n){
        return "Hello kinnu\n".repeat(n);
    }
}