package com.socialchef.service.repositories.services;

import java.util.List;

import com.socialchef.service.models.Product;

public interface ProductService {

    public List<Product> findByName(String name) throws Exception;
    public List<Product> findByCategory(String category) throws Exception;
    public List<Product> findByLocation(String location) throws Exception;
    public List<Product> findByPrice(double price) throws Exception;
    public List<Product> findByRegex(String regex) throws Exception;
    public boolean create(Product product) throws Exception;
    public List<Product> findByUserName(String username) throws Exception;
}
