package com.socialchef.service.repositories.implementation;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.socialchef.service.exceptions.SocialChefException;
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
	public List<Product> findByName(String name) throws Exception {
		if (name != null && !name.isEmpty() &&
				Validator.validateUniqueNames(name))
			return productRepo.findByName(name);
		return null;
	}

	@Transactional
	@Override
	public List<Product> findByCategory(String category)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Product> findByLocation(String location)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Product> findByPrice(double price) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<Product> findByRegex(String regex) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public boolean create(Product product) {
		if (product.validateProduct()) {
			Product saved = productRepo.save(product);
			return saved != null;
		}
		return false;
	}

	@Transactional
	@Override
	public List<Product> findByUserName(String username) {
		if	(username != null && Validator.validateUniqueNames(username)) {
			List<Product> result = productRepo.findByUserName(username);
			return result;
		}
		throw new SocialChefException("Usuario Invalido");
	}

}
