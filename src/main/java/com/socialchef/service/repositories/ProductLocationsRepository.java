package com.socialchef.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialchef.service.models.ProductsLocation;

public interface ProductLocationsRepository extends JpaRepository<ProductsLocation, Long> {

}
