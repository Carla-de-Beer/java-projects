package com.cadebe.github_reader.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public String handleError(Exception exception,  Model model) {
        log.error("Raised exception '{}' when trying to access the Reader", exception.getMessage());
        model.addAttribute("errorMsg", exception.getCause().getMessage());
        return "error";
    }
}
