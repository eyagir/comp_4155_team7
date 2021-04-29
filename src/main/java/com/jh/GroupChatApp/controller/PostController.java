package com.jh.GroupChatApp.controller;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.repository.PostRepository;
import com.jh.GroupChatApp.repository.UserRepository;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;
import java.security.Principal;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PostRepository postRepo;

    @PostMapping("/new")
    public String newPostIndex(Principal principal, Post post) {
        post.setUser(userRepository.findByUsername(principal.getName()));
        post.setCreatedAt(new Timestamp(new Date().getTime()));
        postService.createPost(post);
        return "redirect:/account";
    }

    @PostMapping("/delete/{id}")
    public String deletePostIndex(@PathVariable("id") int id) {
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return "redirect:/account";
    }
}
