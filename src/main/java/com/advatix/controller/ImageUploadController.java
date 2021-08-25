package com.advatix.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.advatix.commons.utils.Constant;
import com.advatix.enums.DeviceType;
import com.advatix.image.upload.service.ImageUploadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_IMAGE_UPLOAD)
@Api(value = Constant.IMAGE_UPLOAD_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"Image Upload Api's" }, hidden = false)
public class ImageUploadController {

	@Autowired
	private ImageUploadService imageUploadService;

	@ApiOperation(value = "image Upload", response = String.class, httpMethod = "POST", notes = "image upload")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Add Author", response = String.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PostMapping("/upload_image")
	@ResponseBody
	public ResponseEntity<String> uploadImage(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion,@RequestParam("file") MultipartFile file)
			throws IOException {
		/*
		 * System.out.println(file.getSize()); System.out.println(file.getName());
		 * System.out.println(file.getContentType());
		 * System.out.println(file.getName());
		 * System.out.println(file.getOriginalFilename());
		 * System.out.println(file.getBytes()); System.out.println(file.getResource());
		 */

		// validation
		try {
			if (file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Request must contain image file");
			}
			if (!file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG File are allowed");
			}
			// image upload code
			boolean result = imageUploadService.uploadImage(file);
			if (result) {
				//return ResponseEntity.ok(" image uploaded succcessfully ");
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image")
						.path(file.getOriginalFilename()).toUriString());
				 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong ! Please try again");

	}

}
