package com.jh.GroupChatApp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    @Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "textbody")
    private String textBody;

    public Long getPostId() { return postId; }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String text) {
        this.textBody = text;
    }

}
