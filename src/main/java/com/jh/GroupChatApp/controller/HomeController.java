package com.jh.GroupChatApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String viewHomePage() {

        return "home";
    }
}
