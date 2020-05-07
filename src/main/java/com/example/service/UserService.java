package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.UserMapper;

@Service
public class UserService {
    
	@Autowired
    UserMapper userMapper;
    
    public int getId(){
        return userMapper.getId();
    }
}
