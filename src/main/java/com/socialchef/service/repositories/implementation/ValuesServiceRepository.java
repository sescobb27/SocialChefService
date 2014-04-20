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
		Query q = entity.createQuery("SELECT new Category(c.name) FROM Category c");
		return q.getResultList();
	}

	public List<Location> getLocations() {
		Query q = entity.createQuery("SELECT new Location(l.name) FROM Location l");
		return q.getResultList();
	}

}
