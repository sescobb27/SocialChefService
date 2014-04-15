package com.socialchef.service.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialchef.service.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

//	======
	@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
	public User findByEmail(@Param("email") String email);
	
//	======
	@Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
	public User findByUsername(@Param("username") String username);
	
}
