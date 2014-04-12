package com.socialchef.service.repositories.services;

import java.util.List;

import com.socialchef.service.models.User;

public interface UserService {

	public User findOne(String query);
	public User findOneById(Long id);
	public List<User> findAll(String query);
	public List<User> findAll(List<Long> ids);
	public boolean delete(Long id);
	public User create(User user);
	public User update(User user);
}
