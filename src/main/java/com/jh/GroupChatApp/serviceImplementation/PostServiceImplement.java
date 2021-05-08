package com.jh.GroupChatApp.serviceImplementation;

import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.FriendshipRepository;
import com.jh.GroupChatApp.repository.PostRepository;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import com.jh.GroupChatApp.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImplement implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    FriendshipService friendshipService;

//    Post selectedPost  = null;
//
//    public Post getSelectedPost(User user) {
//        if (selectedPost.equals(null)) {
//            List<Post> posts = new ArrayList<>();
//            posts = getPostsByUserAndFriends(user);
//            Collections.sort(posts, Comparator.comparing(Post::getCreatedAt));
//            return posts.get(posts.size() - 1);
//        } else
//            return selectedPost;
//    }
//
//    public void setSelectedPost(Post post) {
//        selectedPost = post;
//    }


    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id); }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> getPostsByUserAndFriends(User user) {
        List<Post> posts = new ArrayList<>();
        for (User friend : friendshipService.getFriendsByUser(user)) {
            posts.addAll(getPostsByUser(friend));
        }
        posts.addAll(getPostsByUser(user));
        Collections.sort(posts, Comparator.comparing(Post::getCreatedAt));
        Collections.reverse(posts);
        return posts;
    }
}
