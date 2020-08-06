package com.example.mapper;

import java.util.Map;

public interface UserMapper {

   int getId();
   
   String getUsername();
   
   int insertUser(Map<String, Object> map);
}
