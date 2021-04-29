package com.jh.GroupChatApp.controller;

import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import com.jh.GroupChatApp.serviceInterface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/friendship")
public class FriendshipController {
    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    @GetMapping("/request")
    public String newFriendship(@RequestParam("username") String username, Principal principal, Friendship friendship) {
        friendship.setUser1(userService.getUserByUsername(principal.getName()));
        friendship.setUser2(userService.getUserByUsername(username));
        friendship.setFriendshipConfirmed(false);
        friendshipService.createFriendship(friendship);
        return "redirect:/account";
    }

    @GetMapping("/accept/{username}")
    public String acceptFriendship(@PathVariable("username") String username, Principal principal) {
        User user1 = userService.getUserByUsername(username);
        User user2 = userService.getUserByUsername(principal.getName());
        Friendship friendship = friendshipService.getFriendshipByUsers(user1, user2);
        friendshipService.deleteFriendship(friendship);
        friendship.setFriendshipConfirmed(true);
        friendshipService.createFriendship(friendship);
        return "redirect:/account";
    }
}
