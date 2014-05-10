package com.socialchef.service.repositories.implementation;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialchef.service.exceptions.SocialChefException;
import com.socialchef.service.helpers.Validator;
import com.socialchef.service.models.Product;
import com.socialchef.service.repositories.ProductCategoriesRepository;
import com.socialchef.service.repositories.ProductLocationsRepository;
import com.socialchef.service.repositories.ProductRepository;
import com.socialchef.service.repositories.services.ProductService;

@Service
public class ProductServiceRepository implements ProductService {

	@Resource
	private ProductRepository productRepo;
	@Resource
	private ProductCategoriesRepository productCategoriesRepo;
	@Resource
	private ProductLocationsRepository productLocationsRepo;
	@Autowired
	private EntityManager entity;
	
	public List<Product> findByRate() {
		Query q = entity.createQuery(
				"SELECT new Product(p.name, p.description, p.price, p.image, p.rate) "
				+ "FROM Product p "
				+ "ORDER BY p.rate DESC", Product.class);
		return q.getResultList();
	}

	@Transactional
	@Override
	public List<Product> findByName(String name) {
		if (name != null && !name.isEmpty() &&
				Validator.validateProductName(name)) {
			return productRepo.findByName(name.toLowerCase());
		}
		throw new SocialChefException("Nombre del Producto Invalido");
	}

	@Transactional
	@Override
	public List<Product> findByCategory(String category) {
		if (category != null && !category.isEmpty() &&
				Validator.validateTextOnly(category))
			return productRepo.findByCategory(category);
		throw new SocialChefException("Categoria Invalida");
	}

	@Transactional
	@Override
	public List<Product> findByLocation(String location) {
		if (location != null && !location.isEmpty() &&
				Validator.validateTextOnly(location))
			return productRepo.findByLocation(location);
		throw new SocialChefException("Ubicacion Invalida");
	}

	@Transactional
	@Override
	public List<Product> findByPrice(double bottom, double top) {
		if	(bottom > 0.0 && bottom <= Double.MAX_VALUE && 
				top > 0.0 && top <= Double.MAX_VALUE)
			return productRepo.findByPrice(bottom, top);
		throw new SocialChefException("Precio Invalido");
	}

	@Transactional
	@Override
	public boolean create(Product product) {
		if (product.validateProduct()) {
			Product saved = productRepo.save(product);
			productCategoriesRepo.save(product.getProductsCategories());
			productLocationsRepo.save(product.getProductsLocations());
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
