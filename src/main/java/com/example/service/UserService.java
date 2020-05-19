package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.UserMapper;

@Service
public class UserService {
    
	@Autowired
    UserMapper userMapper;
    
	@Transactional
    public int getId(){
        
    	int id = userMapper.getId();
    	
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	return id;
    }
    
	@Transactional
    public String getUsername(){
        return userMapper.getUsername();
    }
}
