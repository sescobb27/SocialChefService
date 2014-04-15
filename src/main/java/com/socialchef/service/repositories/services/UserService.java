package com.socialchef.service.repositories.services;

import java.util.List;

import com.socialchef.service.models.User;

public interface UserService {

	public User findOne(String query) throws Exception;
	public User findOneById(Long id) throws Exception;
	public List<User> findAll(String query) throws Exception;
	public List<User> findAll(List<Long> ids) throws Exception;
	public boolean delete(Long id) throws Exception;
	public boolean create(User user) throws Exception;
	public User update(User user) throws Exception;
}
