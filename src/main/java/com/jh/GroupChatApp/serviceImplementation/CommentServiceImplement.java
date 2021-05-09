package com.jh.GroupChatApp.serviceImplementation;

import com.jh.GroupChatApp.model.Comment;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.CommentRepository;
import com.jh.GroupChatApp.repository.PostRepository;
import com.jh.GroupChatApp.serviceInterface.CommentService;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CommentServiceImplement implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostService postService;

    @Autowired
    FriendshipService friendshipService;

    @Override
    public List<Comment> getCommentsByPost(Post post) {
        List<Comment> comments = new ArrayList<>();
        comments.addAll(commentRepository.findAllByPost(post));
        Collections.sort(comments, Comparator.comparing(Comment::getCreatedAt));
        Collections.reverse(comments);
        return comments;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
