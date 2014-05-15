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
	
	@Query("SELECT new User(u.id, u.name, u.username, u.email) FROM Product p "
			+ "INNER JOIN p.user u WHERE p.id = :p_id")
	public User findUserByProductId(@Param("p_id") Integer p_id);
}
