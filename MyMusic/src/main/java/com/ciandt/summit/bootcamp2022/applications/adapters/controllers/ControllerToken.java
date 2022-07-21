package com.ciandt.summit.bootcamp2022.applications.adapters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class ControllerToken {
    @GetMapping(value = "/inter")
    public String getHealthCheck(){
        return "200 ok";
    }
}
