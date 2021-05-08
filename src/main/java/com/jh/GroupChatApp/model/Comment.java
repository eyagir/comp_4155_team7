package com.jh.GroupChatApp.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentid")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "postid")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "createdat")
    private Timestamp createdAt;

    @Column(name = "textbody")
    private String textBody;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }
}
