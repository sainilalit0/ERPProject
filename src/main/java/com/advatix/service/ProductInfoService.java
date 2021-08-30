package com.advatix.service;

import java.util.List;

import com.advatix.entities.ProductInfo;

public interface ProductInfoService {

	ProductInfo addProductDetails(ProductInfo productInfo);

	List<ProductInfo> listOfProductInfos();

	ProductInfo findProductInfoById(int id);

	void updateProductInfo(int id, ProductInfo productInfo);

	void deleteProductInfo(int id);

}
