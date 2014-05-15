package com.socialchef.service.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialchef.service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate) "
			+ "FROM Product p WHERE LOWER(p.name) LIKE %:name% ORDER BY p.rate DESC")
    public List<Product> findByName(@Param("name") String name);

//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate) "
			+ "FROM Product p INNER JOIN p.categories pc INNER JOIN pc.category c "
			+ "WHERE LOWER(c.name) = LOWER(:category) ORDER BY p.rate DESC")
    public List<Product> findByCategory(@Param("category") String category);

//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate) "
			+ "FROM Product p INNER JOIN p.locations pl INNER JOIN pl.location l "
			+ "WHERE LOWER(l.name) = LOWER(:location) ORDER BY p.rate DESC")
    public List<Product> findByLocation(@Param("location") String location);

//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate)"
			+ "FROM Product p INNER JOIN p.user u "
			+ "WHERE LOWER(u.username) = LOWER(:username)")
	public List<Product> findByUserName(@Param("username") String username);

//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate) "
			+ "FROM Product p WHERE p.price >= :bottom AND p.price <= :top "
			+ "ORDER BY p.price ASC")
    public List<Product> findByPrice(@Param("bottom") double bottom, 
    		@Param("top") double top);
	
//	======
	@Query("SELECT new Product(p.id, p.name, p.description, p.price, p.image, p.rate) "
			+ "FROM Product p WHERE p.id = :id")
	public Product findById(@Param("id") Integer id);

}
