package com.jh.GroupChatApp.model;

import javax.persistence.*;
import com.jh.GroupChatApp.model.User;

@Entity
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friendshipid")
    private Long friendshipId;

    @ManyToOne
    @JoinColumn(name = "userid1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "userid2")
    private User user2;

    @Column(name = "friendshipconfirmed")
    private boolean friendshipConfirmed;

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public boolean isFriendshipConfirmed() {
        return friendshipConfirmed;
    }

    public void setFriendshipConfirmed(boolean friendshipConfirmed) {
        this.friendshipConfirmed = friendshipConfirmed;
    }
}
