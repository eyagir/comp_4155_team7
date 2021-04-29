package com.jh.GroupChatApp.serviceInterface;

import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.Post;
import com.jh.GroupChatApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface FriendshipService {

    public List<Friendship> getFriendshipsByUser(User user);

    public List<User> getFriendsByUser(User user);

    public List<User> getFriendRequestsFrom(User user);

    public List<User> getFriendRequestsTo(User user);

    public Friendship createFriendship(Friendship friendship);

    public void deleteFriendship(Friendship friendship);

    public Friendship getFriendshipByUsers(User user1, User user2);
}
