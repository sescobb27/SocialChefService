package com.socialchef.service.repositories.implementation;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialchef.service.exceptions.SocialChefException;
import com.socialchef.service.helpers.Encryption;
import com.socialchef.service.helpers.Validator;
import com.socialchef.service.models.User;
import com.socialchef.service.repositories.UserRepository;
import com.socialchef.service.repositories.services.UserService;

@Service
public class UserServiceRepository implements UserService {

	@Resource
	private UserRepository userRepo;
	
	@Override
	public User findOne(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOneById(Long id) throws Exception {
		if (id == null) {
			throw new NullPointerException("Id is null");
		} else if (id < 0) {
			throw new IllegalArgumentException("Id is less than 0");
		} else if (id > Long.MAX_VALUE) {
			throw new Exception("Id is bigger than the max possible value");
		} else {
			return userRepo.findOne(id);
		}
	}

	@Override
	public List<User> findAll(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public boolean delete(Long id) throws Exception {
		if (id == null) {
			throw new NullPointerException("Id is null");
		} else if (id < 0) {
			throw new IllegalArgumentException("Id is less than 0");
		} else if (id > Long.MAX_VALUE) {
			throw new Exception("Id is bigger than the max possible value");
		} else {
			userRepo.delete(id);
			return !userRepo.exists(id);
		}
	}

	@Transactional
	@Override
	public boolean create(User user) {
		if (user.validateUser()) {
			if (findByUsername(user.getUsername()) != null) {
				user.addError("El usuario ya existe");
				return false;
			} else if (findByEmail(user.getEmail())!= null) {
				user.addError("Ya existe un usuario con ese correo");
				return false;
			}
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsername(String username) {
		String tmp_username = username.trim().toLowerCase();
		if (tmp_username != null && Validator.validateUniqueNames(tmp_username)) {
			return userRepo.findByUsername(tmp_username);
		}
		throw new SocialChefException("Usuario Invalido");
	}

	public User findByEmail(String email) {
		String tmp_email = email.trim().toLowerCase();
		if (tmp_email != null && Validator.validateEmail(email)) {
			return userRepo.findByEmail(tmp_email);
		}
		throw new SocialChefException("Correo Invalido");
	}
	
	public boolean validateLogin(String username, String password) {
		User u = findByUsername(username);
		try {
			String []encrypt = {password,u.getCreatedAt().toString()};
			String new_pass = Encryption.encryptSHA256(encrypt);
			return new_pass.equalsIgnoreCase(u.getPassword());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
    /**
     * This setter method should be used only by unit tests.
     * @param personRepository
     */
    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

}
