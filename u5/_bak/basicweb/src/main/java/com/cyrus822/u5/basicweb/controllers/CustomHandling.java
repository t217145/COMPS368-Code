package com.cyrus822.u5.basicweb.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cyrus822.u5.basicweb.utils.CustomException;

@ControllerAdvice
public class CustomHandling {
    
    @ExceptionHandler({Exception.class})
    public String error(Exception err){
        return "error";
    }

}
