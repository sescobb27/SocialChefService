package com.socialchef.service.repositories.implementation;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialchef.service.exceptions.SocialChefException;
import com.socialchef.service.helpers.Validator;
import com.socialchef.service.models.Bill;
import com.socialchef.service.models.Product;
import com.socialchef.service.repositories.BillRepository;


@Service
public class BillServiceRepository {
	@Resource
	private BillRepository billRepo;
	@Autowired
	private EntityManager entity;
	
	@Transactional
		public List<Bill> selectAll() {
		
			return billRepo.selectAll();
		
		
	}
}
