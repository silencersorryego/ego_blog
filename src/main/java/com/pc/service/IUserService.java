package com.pc.service;


import com.pc.domain.User;

public interface IUserService {
    User getUserByUserName(String username);
    User checkUser(String username,String password);
}
