package com.socialchef.service.repositories.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialchef.service.models.Category;
import com.socialchef.service.models.Location;

@Service
public class ValuesServiceRepository {

	@Autowired
	private EntityManager entity;
	
	public List<Category> getCategories() {
		Query q = entity.createQuery("SELECT new Category(c.name) FROM Category c",
				Category.class);
		return q.getResultList();
	}

	public List<Location> getLocations() {
		Query q = entity.createQuery("SELECT new Location(l.name) FROM Location l",
				Location.class);
		return q.getResultList();
	}
	
	public List<Category> getCategoriesByName(List<String> categories) {
		Query q = entity.createQuery("SELECT new Category(c.id, c.name) FROM Category c "
				+ "WHERE c.name IN :names", Category.class);
		q.setParameter("names", categories);
		return q.getResultList();
	}
	
	public List<Location> getLocationsByName(List<String> locations) {
		Query q = entity.createQuery("SELECT new Location(l.id, l.name) FROM Location l "
				+ "WHERE l.name IN :names", Location.class);
		q.setParameter("names", locations);
		return q.getResultList();
	}	

}
