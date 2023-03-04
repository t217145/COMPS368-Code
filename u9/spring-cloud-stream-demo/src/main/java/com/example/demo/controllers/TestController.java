package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
	private StreamBridge bridge;

    @GetMapping("/send")
    public String send(@RequestParam("message") String msg){
        boolean isSuccess = bridge.send("sendOut-out-0", msg);
        return isSuccess ? "Success" : "Failed";
    }
}
