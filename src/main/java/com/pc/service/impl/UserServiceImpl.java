package com.pc.service.impl;


import com.pc.domain.User;
import com.pc.mapper.IUserMapper;
import com.pc.service.IUserService;
import com.pc.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserMapper userMapper;


    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public User checkUser(String username,String password) {
        User u = userMapper.getUserByUserName(username);
        //System.out.println(u.getPassword().equals(MD5Utils.code(password)));
        if ( u!=null && u.getPassword().equals(MD5Utils.code(password))){
            return u;
        }
        return null;
    }
}
