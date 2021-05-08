package com.jh.GroupChatApp.controller;

import com.jh.GroupChatApp.model.Comment;
import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.UserRepository;
import com.jh.GroupChatApp.serviceInterface.CommentService;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@SessionAttributes("selectedPost")
public class AccountController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendshipService friendshipService;

    @GetMapping("/account")
    public String viewAccountPage(Model model, Principal principal, HttpSession session) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getPostsByUserAndFriends(user));
        model.addAttribute("post", new Post());
        if (session.getAttribute("selectedPost") == null) {
            if (!postService.getPostsByUserAndFriends(user).isEmpty()) {
                model.addAttribute("comments", commentService.getCommentsByPost(postService.getPostsByUserAndFriends(user).get(0)));
            }
        } else {
            model.addAttribute("comments", commentService.getCommentsByPost(postService.getPostById((int)session.getAttribute("selectedPost"))));
        }
        model.addAttribute("friends", friendshipService.getFriendsByUser(user));
        model.addAttribute("friendRequests", friendshipService.getFriendRequestsTo(user));
        model.addAttribute("newFriendRequest", new Friendship());
        model.addAttribute("acceptFriendRequest", new Friendship());
        model.addAttribute("newpost", new Post());
        model.addAttribute("newcomment", new Comment());
        model.addAttribute("commentdelete", new Comment());
        return "account";
    }
}
