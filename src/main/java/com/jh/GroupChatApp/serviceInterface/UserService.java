package com.jh.GroupChatApp.serviceInterface;

import com.jh.GroupChatApp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserByUsername(String username);
    User getUserByUserId(Long userId);
}
