package com.jh.GroupChatApp.repository;

import com.jh.GroupChatApp.model.Friendship;
import com.jh.GroupChatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    @Query("select f from Friendship f where (f.user1 = ?1 or f.user2 = ?1) and f.friendshipConfirmed = true")
    List<Friendship> findAllByUser1OrUser2AndFriendshipConfirmedIsTrue(User user);

    List<Friendship> findAllByUser1AndFriendshipConfirmedIsFalse(User user);
    List<Friendship> findAllByUser2AndFriendshipConfirmedIsFalse(User user);
    Friendship findByUser1AndUser2(User user1, User user2);
    Friendship findByUser2AndUser1(User user1, User user2);

}
