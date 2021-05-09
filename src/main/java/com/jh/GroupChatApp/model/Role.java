package com.jh.GroupChatApp.model;

import javax.persistence.*;

@Entity

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid")
    private User user;

    @Column(name = "rolename")
    private String roleName;


}
