package com.finalproject.SuperDuperDrive.FinalProject.service;

import com.finalproject.SuperDuperDrive.FinalProject.mapper.UserMapper;
import com.finalproject.SuperDuperDrive.FinalProject.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    //存取user的地方 因此UserMapper是@Mapper
    private final UserMapper userMapper;
    //針對user的password進行hash工作 因此HashService是@Component
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService){
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        //看能不能在這裡抓到物件 如果不能代表資料庫沒有這個名字
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        //取random的Salt random.nextBytes will put the number into the byte[] salt
        random.nextBytes(salt);
        //encode salt by Base64
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

}
