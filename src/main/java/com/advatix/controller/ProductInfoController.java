package com.advatix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.advatix.commons.utils.Constant;
import com.advatix.entities.ProductInfo;
import com.advatix.enums.DeviceType;
import com.advatix.service.ProductInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_PRODUCT)
@Api(value = Constant.PRODUCT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"Product Info Api's" }, hidden = false)
public class ProductInfoController {

	@Autowired
	private ProductInfoService productInfoService;

	@ApiOperation(value = "Add Product info details", response = ProductInfo.class, httpMethod = "POST", notes = "Add Product details")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Add product info details", response = ProductInfo.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PostMapping("/addProductDetails")
	@ResponseBody
	public ResponseEntity<ProductInfo> addProductDetails(
			@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @RequestBody ProductInfo productInfo) {
		@SuppressWarnings("unused")
		ProductInfo productInfoObject = new ProductInfo();

		try {
			productInfoObject = this.productInfoService.addProductDetails(productInfo);
			return ResponseEntity.status(HttpStatus.CREATED).body(productInfoObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Get Product info", response = ProductInfo.class, httpMethod = "GET", notes = "Get All Product Info")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All", response = ProductInfo.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/listOfProductInfos")
	@ResponseBody
	public ResponseEntity<List<ProductInfo>> listOfProductInfos(
			@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion) {
		List<ProductInfo> ProductInfoLists = this.productInfoService.listOfProductInfos();
		if (ProductInfoLists.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(ProductInfoLists));
		}
	}

	@ApiOperation(value = "Get ProductInfo", response = ProductInfo.class, httpMethod = "GET", notes = "find By Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Find By Id", response = ProductInfo.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/findProductInfoById/{id}")
	@ResponseBody
	public ResponseEntity<ProductInfo> findProductInfoById(
			@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {
		ProductInfo ProductInfo = this.productInfoService.findProductInfoById(id);
		if (ProductInfo == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(ProductInfo));
	}

	@ApiOperation(value = "Update ProductInfo", response = ProductInfo.class, httpMethod = "PUT", notes = "Update ProductInfo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update ProductInfo", response = ProductInfo.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PutMapping("/updateProductInfo/{id}")
	@ResponseBody
	public ResponseEntity<ProductInfo> updateProductInfo(
			@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id,
			@RequestBody ProductInfo ProductInfo) {
		try {
			this.productInfoService.updateProductInfo(id, ProductInfo);
			return ResponseEntity.ok().body(ProductInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Delete ProductInfo", response = ProductInfo.class, httpMethod = "DELETE", notes = "Delete ProductInfo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Delete ProductInfo", response = ProductInfo.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@DeleteMapping("/deleteProductInfo/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteProductInfo(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {
		try {
			this.productInfoService.deleteProductInfo(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
