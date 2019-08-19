package com.ecjtu.amovie.service;

import com.ecjtu.amovie.entity.User;
import com.ecjtu.amovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServce {
    @Autowired
    private UserRepository userRepository;
    public int saveOne(User user){
        return userRepository.saveOne(user);
    }

    public User findOneByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    public boolean isEmailExit(String email){
        int count = userRepository.isEmailExit(email);
        return count!=0;
    }
}
