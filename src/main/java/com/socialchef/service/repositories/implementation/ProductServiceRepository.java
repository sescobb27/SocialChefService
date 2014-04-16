package com.socialchef.service.repositories.implementation;

import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialchef.service.helpers.Validator;
import com.socialchef.service.models.Product;
import com.socialchef.service.repositories.ProductRepository;
import com.socialchef.service.repositories.services.ProductService;

@Service
public class ProductServiceRepository implements ProductService {

	@Resource
	private ProductRepository productRepo;

	@Transactional
	@Override
	public Set<Product> findByName(String name) throws Exception {
		if (name != null && !name.isEmpty() &&
				Validator.validateUniqueNames(name))
			return productRepo.findByName(name);
		return null;
	}

	@Transactional
	@Override
	public Set<Product> findByCategory(String category)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Set<Product> findByLocation(String location)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Set<Product> findByPrice(double price) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public Set<Product> findByRegex(String regex) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
