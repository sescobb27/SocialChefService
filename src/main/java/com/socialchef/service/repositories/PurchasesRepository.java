package com.socialchef.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialchef.service.models.Purchase;

public interface PurchasesRepository extends JpaRepository<Purchase, Long> {

}
