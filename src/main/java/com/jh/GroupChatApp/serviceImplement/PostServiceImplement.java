package com.jh.GroupChatApp.serviceImplement;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.PostRepository;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImplement implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
