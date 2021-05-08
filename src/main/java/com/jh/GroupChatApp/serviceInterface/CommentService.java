package com.jh.GroupChatApp.serviceInterface;

import com.jh.GroupChatApp.model.Comment;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getCommentsByPost(Post post);

    Comment createComment(Comment comment);

    Comment getCommentById(int id);

    void deleteComment(Comment comment);
}
