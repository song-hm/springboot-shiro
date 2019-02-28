package com.shm.mapper;

import com.shm.domain.User;

public interface UserMapper {

	public User findByName(String name);
	public User findById(Integer id);
}
