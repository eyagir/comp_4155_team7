package com.jh.GroupChatApp.controllers;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupChatDataController {
    
    // @Autowired
    // private GroupChatDataService service;

    @RequestMapping("/")
    public String index() {
        
        return "Hello Team. Lets get to work";
    }
    
}
