package com.socialchef.service.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialchef.service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

//	======
	@Query("SELECT new Product(p.name, p.description, p.price, p.image) "
			+ "FROM Product p WHERE LOWER(p.name) LIKE %:name%")
    public List<Product> findByName(@Param("name") String name);

//	======
	@Query("SELECT new Product(p.name, p.description, p.price, p.image) "
			+ "FROM Product p INNER JOIN p.categories pc INNER JOIN pc.category c "
			+ "WHERE LOWER(c.name) = LOWER(:category)")
    public List<Product> findByCategory(@Param("category") String category);

//	======
	@Query("SELECT new Product(p.name, p.description, p.price, p.image) "
			+ "FROM Product p INNER JOIN p.locations pl INNER JOIN pl.location l "
			+ "WHERE LOWER(l.name) = LOWER(:location)")
    public List<Product> findByLocation(@Param("location") String location);

//	======
	@Query("SELECT new Product(p.name, p.description, p.price, p.image)"
			+ "FROM Product p INNER JOIN p.user u "
			+ "WHERE LOWER(u.username) = LOWER(:username)")
	public List<Product> findByUserName(@Param("username") String username);

//	======
//	@Query("")
//    public List<Product> findByPrice(@Param("price") double price);

//	======
//	@Query("")
//    public List<Product> findByRegex(@Param("regex") String regex);
}
