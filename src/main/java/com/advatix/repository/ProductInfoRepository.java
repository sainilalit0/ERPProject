package com.advatix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advatix.entities.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

}
