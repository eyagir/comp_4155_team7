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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/delete/{id}")
    public String deletePostIndex(@PathVariable("id") int id) {
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return "redirect:/account";
    }

    @PostMapping("/{id}")
    public String viewPostComments(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("postSelected", id);

        return "redirect:/account";
    }
}
