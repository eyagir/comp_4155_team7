package com.jh.GroupChatApp.serviceInterface;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<Post> getPostsByUser(User user);

    Post createPost(Post post);

    Post getPostById(int id);

    void deletePost(Post post);
}
