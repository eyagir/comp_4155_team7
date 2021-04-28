package com.jh.GroupChatApp.repository;

import com.jh.GroupChatApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
