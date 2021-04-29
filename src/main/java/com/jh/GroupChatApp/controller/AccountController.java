package com.jh.GroupChatApp.controller;

import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.FriendshipRepository;
import com.jh.GroupChatApp.repository.UserRepository;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipService friendshipService;

    @GetMapping("/account")
    public String viewAccountPage(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getPostsByUser(user));
        model.addAttribute("friends", friendshipService.getFriendsByUser(user));
        model.addAttribute("friendRequests", friendshipService.getFriendRequestsTo(user));
        model.addAttribute("newFriendRequest", new Friendship());
        model.addAttribute("acceptFriendRequest", new Friendship());
        model.addAttribute("newpost", new Post());
        model.addAttribute("postdelete", new Post());
        return "account";
    }
}
