package com.shm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shm.domain.User;
import com.shm.mapper.UserMapper;
import com.shm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByName(String name) {
		return userMapper.findByName(name);
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

}
