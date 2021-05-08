package com.jh.GroupChatApp.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.serviceInterface.CommentService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import com.jh.GroupChatApp.serviceInterface.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public String newPost(Principal principal, Post post) {
        post.setUser(userService.getUserByUsername(principal.getName()));
        post.setCreatedAt(new Timestamp(new Date().getTime()));
        postService.createPost(post);
        return "redirect:/account";
    }

    @PostMapping(value="/{id}", params="action=delete")
    public String deletePost(@PathVariable("id") int id) {
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return "redirect:/account";
    }

    @PostMapping(value="/{id}", params="action=comments")
    public String viewPostComments(@PathVariable("id") int id, HttpSession session) {
        session.setAttribute("selectedPost", id);
        return "redirect:/account";
    }
}
