package com.jh.GroupChatApp.repository;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);
    Post findById(long id);
}
