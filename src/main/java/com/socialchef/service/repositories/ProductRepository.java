package com.socialchef.service.repositories;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialchef.service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

//	======
	@Query("SELECT p FROM Product p RIGHT JOIN FETCH p.user WHERE LOWER(p.name) = LOWER(:name)")
    public Set<Product> findByName(@Param("name") String name);
	
//	======
	@Query("SELECT p FROM Product p INNER JOIN p.categories pc INNER JOIN pc.category c WHERE LOWER(c.name) = LOWER(:category)")
    public Set<Product> findByCategory(@Param("category") String category);
	
//	======
	@Query("SELECT p FROM Product p INNER JOIN p.locations pl INNER JOIN pl.location l WHERE LOWER(l.name) = LOWER(:location)")
    public Set<Product> findByLocation(@Param("location") String location);
	
//	======
//	@Query("")
//    public LinkedHashSet<Product> findByPrice(@Param("price") double price);
	
//	======
//	@Query("")
//    public LinkedHashSet<Product> findByRegex(@Param("regex") String regex);
}
