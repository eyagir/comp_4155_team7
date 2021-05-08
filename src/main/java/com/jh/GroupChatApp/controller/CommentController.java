package com.jh.GroupChatApp.controller;

import com.jh.GroupChatApp.model.Comment;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.serviceInterface.CommentService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import com.jh.GroupChatApp.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/new")
    public String newComment(Principal principal, Comment comment, RedirectAttributes redirectAttributes) {
        User user = userService.getUserByUsername(principal.getName());
        comment.setUser(user);
        comment.setCreatedAt(new Timestamp(new Date().getTime()));
        if (redirectAttributes.getAttribute("selectedPost") == null) {
            List<Post> posts = new ArrayList<>();
            posts = postService.getPostsByUserAndFriends(user);
            Collections.sort(posts, Comparator.comparing(Post::getCreatedAt));
            comment.setPost(posts.get(posts.size() - 1));
        } else {
            comment.setPost(postService.getPostById((int)redirectAttributes.getAttribute("selectedPost")));
        }
        commentService.createComment(comment);
        return "redirect:/account";
    }

    @PostMapping("/delete/{id}")
    public String deleteCommentIndex(@PathVariable("id") int id) {
        Comment comment = commentService.getCommentById(id);
        commentService.deleteComment(comment);
        return "redirect:/account";
    }
}
