package com.shm.service;

import com.shm.domain.User;

public interface UserService {

	public User findByName(String name);
	public User findById(Integer id);
}
