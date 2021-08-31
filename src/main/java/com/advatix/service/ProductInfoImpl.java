package com.advatix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.ProductInfo;
import com.advatix.exception.ResourceNotFoundException;
import com.advatix.repository.ProductInfoRepository;

@Service
public class ProductInfoImpl implements ProductInfoService {

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	@Transactional
	public ProductInfo addProductDetails(ProductInfo productInfo) {
		// validation for product type

		if (productInfo.getWeight() < 10) {
			productInfo.setCost(100);
			productInfo.setDeliveryOfDays(2);

		} else if (productInfo.getWeight() < 20) {
			productInfo.setCost(200);
			productInfo.setDeliveryOfDays(3);
		} else if (productInfo.getWeight() < 30) {
			productInfo.setCost(300);
			productInfo.setDeliveryOfDays(4);

		} else if (productInfo.getWeight() < 40) {
			productInfo.setCost(400);
			productInfo.setDeliveryOfDays(5);

		} else if (productInfo.getWeight() < 50) {
			productInfo.setCost(500);
			productInfo.setDeliveryOfDays(6);

		} else if (productInfo.getWeight() < 60) {
			productInfo.setCost(600);
			productInfo.setDeliveryOfDays(7);
		}
		productInfoRepository.save(productInfo);
		return productInfo;
	}

	@Override
	@Transactional
	public List<ProductInfo> listOfProductInfos() {
		return productInfoRepository.findAll();
	}

	@Override
	@Transactional
	public ProductInfo findProductInfoById(int id) {
		ProductInfo productInfoId = productInfoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID :" + id + " Not Found!"));
		return productInfoId;
	}

	@Override
	@Transactional
	public void updateProductInfo(int id, ProductInfo productInfo) {
		ProductInfo productInfoId = productInfoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID :" + id + " Not Found!"));
		productInfo.setId(productInfoId.getId());
		productInfoRepository.save(productInfo);

	}

	@Override
	@Transactional
	public void deleteProductInfo(int id) {
		ProductInfo productInfo = productInfoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product with ID :" + id + " Not Found!"));
		productInfoRepository.deleteById(productInfo.getId());
	}

}
