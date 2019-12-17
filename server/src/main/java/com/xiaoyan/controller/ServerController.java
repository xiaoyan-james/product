package com.xiaoyan.controller;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Logger
public class ServerController {
    @GetMapping("/msg")
    public String msg(){
        return  "this is product server-2";
    }
}
