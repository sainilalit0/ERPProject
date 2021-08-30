package com.advatix.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.advatix.commons.utils.Constant;
import com.advatix.entities.Author;
import com.advatix.entities.Bmi;
import com.advatix.enums.DeviceType;
import com.advatix.service.BmiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_BMI)
@Api(value = Constant.BMI_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"BMI calculator Api's" }, hidden = false)
public class BmiController {

	@Autowired
	private BmiService bmiService;
	
	@ApiOperation(value = "Add bmi detail", response = Bmi.class, httpMethod = "POST", notes = "calculate Bmi")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Add Author", response = Bmi.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@PostMapping("/calculateBmi")
	@ResponseBody
	public ResponseEntity<Bmi> calculateBmi(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @RequestBody Bmi bmi) {
		Bmi bmiObject = new Bmi();

		try {
			bmiObject = this.bmiService.calculateBmi(bmi);
			return ResponseEntity.status(HttpStatus.CREATED).body(bmiObject);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "Get List Bmi", response = Author.class, httpMethod = "GET", notes = "Get All list Bmi")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@GetMapping("/listOfBmi")
	@ResponseBody
	public ResponseEntity<List<Bmi>> listOfBmi(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion) {
		List<Bmi> bmiList = this.bmiService.listOfBmi();
		if (bmiList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(bmiList));
		}
	}
	
}
