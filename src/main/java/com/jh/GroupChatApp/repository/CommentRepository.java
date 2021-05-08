package com.jh.GroupChatApp.repository;

import com.jh.GroupChatApp.model.Comment;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
    Comment findById(long id);
}
