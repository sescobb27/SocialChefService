package com.socialchef.service.repositories.services;

import java.util.Set;

import com.socialchef.service.models.Product;

public interface ProductService {

    public Set<Product> findByName(String name) throws Exception;
    public Set<Product> findByCategory(String category) throws Exception;
    public Set<Product> findByLocation(String location) throws Exception;
    public Set<Product> findByPrice(double price) throws Exception;
    public Set<Product> findByRegex(String regex) throws Exception;
    public boolean create(Product product) throws Exception;
    public Set<Product> findByUserName(String username) throws Exception;
}
