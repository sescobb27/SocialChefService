package com.socialchef.service.repositories.implementation;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialchef.service.models.User;
import com.socialchef.service.repositories.UserRepository;
import com.socialchef.service.repositories.services.UserService;

@Service
public class UserServiceRepository implements UserService {

	@Resource
	private UserRepository userRepo;
	
	@Transactional()
	@Override
	public User findOne(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public User findOneById(Long id) {
		if (id != null) {
			return userRepo.findOne(id);
		}
		throw new NullPointerException("Id is null");
	}

	@Transactional
	@Override
	public List<User> findAll(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<User> findAll(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		if (username != null && !username.equals("")) {
			return userRepo.findByUsername(username);
		}
		throw new NullPointerException("Id is null");
	}

	@Transactional
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
    /**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

}
