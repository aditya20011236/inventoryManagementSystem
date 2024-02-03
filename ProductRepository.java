package com.mydata.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mydata.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	

}
