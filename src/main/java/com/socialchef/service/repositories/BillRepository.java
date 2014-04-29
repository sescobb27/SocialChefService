package com.socialchef.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialchef.service.models.Bill;


public interface BillRepository extends JpaRepository<Bill, Long> {
	
//	======
	@Query("SELECT p FROM Bill p ")
    public List<Bill> selectAll();

//	======

}
