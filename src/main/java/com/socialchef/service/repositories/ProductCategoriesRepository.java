package com.socialchef.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialchef.service.models.ProductsCategory;

public interface ProductCategoriesRepository extends JpaRepository<ProductsCategory, Long> {

}
