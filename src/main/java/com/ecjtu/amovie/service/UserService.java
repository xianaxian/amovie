package com.ecjtu.amovie.service;

import com.ecjtu.amovie.entity.User;
import com.ecjtu.amovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xianaixan
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int saveOne(User user){
        return userRepository.saveOne(user);
    }

    public User findOneByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    public boolean isEmailExists(String email){
        int count = userRepository.isEmailExists(email);
        return count!=0;
    }
}
