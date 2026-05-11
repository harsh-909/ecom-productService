package com.ecom.productService.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class TestController {

    @GetMapping("/say/{n}")
    public void sayHello(@RequestParam("n") int n){
        for(int i = 0; i < n; i++){
            System.out.println("Hello kinnu");
        }
    }
}
