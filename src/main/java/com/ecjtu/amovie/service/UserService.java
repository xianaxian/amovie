package com.ecjtu.amovie.service;

import com.ecjtu.amovie.config.GlobalExceptionHandler;
import com.ecjtu.amovie.entity.User;
import com.ecjtu.amovie.repository.UserRepository;
import com.ecjtu.amovie.utils.MD5Utils;
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

    public int register(User user)  {
        if (isEmailExists(user.getEmail())){
            throw new RuntimeException(user.getEmail()+"该用户已经注册");
        }
        String salt = MD5Utils.getSalt();
        user.setSalt(salt);
        user.setPassword(MD5Utils.md5(user.getPassword(), salt));
        return userRepository.saveOne(user);
    }

    public User findOneByEmail(String email){
        return userRepository.findOneByEmail(email);
    }

    public  boolean isEmailExists(String email){
        int count = userRepository.isEmailExists(email);
        return count!=0;
    }
}
