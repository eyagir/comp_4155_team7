package com.jh.GroupChatApp.serviceImplementation;

import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.User;
import com.jh.GroupChatApp.repository.FriendshipRepository;
import com.jh.GroupChatApp.serviceInterface.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipServiceImplement implements FriendshipService {

    @Autowired
    FriendshipRepository friendshipRepository;

    @Override
    public List<Friendship> getFriendshipsByUser(User user) {

        return friendshipRepository.findAllByUser1OrUser2AndFriendshipConfirmedIsTrue(user);
    }

    @Override
    public List<User> getFriendsByUser(User user) {
        List<User> friends = new ArrayList<>();
        for  (Friendship friendship : getFriendshipsByUser(user)) {
            if (friendship.getUser1().equals(user)) {
                friends.add(friendship.getUser2());
            } else
                friends.add(friendship.getUser1());
        }
        return friends;
    }

    @Override
    public List<User> getFriendRequestsFrom(User user) {
        List<User> friends = new ArrayList<>();
        for  (Friendship friendship : friendshipRepository.findAllByUser1AndFriendshipConfirmedIsFalse(user)) {
                friends.add(friendship.getUser2());
        }
        return friends;
    }

    @Override
    public List<User> getFriendRequestsTo(User user) {
        List<User> friends = new ArrayList<>();
        for  (Friendship friendship : friendshipRepository.findAllByUser2AndFriendshipConfirmedIsFalse(user)) {
                friends.add(friendship.getUser1());
        }
        return friends;
    }

    @Override
    public Friendship createFriendship(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public void deleteFriendship(Friendship friendship) {

        friendshipRepository.delete(friendship);
    }

    @Override
    public Friendship getFriendshipByUsers(User user1, User user2) {
        if (friendshipRepository.findByUser1AndUser2(user1, user2) != null) {
            return friendshipRepository.findByUser1AndUser2(user1, user2);
            } else {
            return friendshipRepository.findByUser2AndUser1(user1, user2);
            }
    }
}
