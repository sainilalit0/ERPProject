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
import com.advatix.entities.Student;
import com.advatix.enums.DeviceType;
import com.advatix.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_STUDENT)
@Api(value = Constant.STUDENT_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"Student Api's" }, hidden = false)
public class StudentController {

	@Autowired
	private StudentService studentService;

	@ApiOperation(value = "Add Student", response = Student.class, httpMethod = "POST", notes = "Add Student")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Add Author", response = Student.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PostMapping("/addStudent")
	@ResponseBody
	public ResponseEntity<Student> addStudent(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @RequestBody Student student) {
		@SuppressWarnings("unused")
		Student studentObject = new Student();

		try {
			studentObject = this.studentService.addStudent(student);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Get Students", response = Student.class, httpMethod = "GET", notes = "Get All Studens")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All", response = Student.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/listOfstudents")
	@ResponseBody
	public ResponseEntity<List<Student>> listOfstudents(
			@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion) {
		List<Student> studentLists = this.studentService.listOfstudents();
		if (studentLists.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(studentLists));
		}
	}

	@ApiOperation(value = "Get student", response = Student.class, httpMethod = "GET", notes = "find By Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Find By Id", response = Student.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/findStudentById/{id}")
	@ResponseBody
	public ResponseEntity<Student> findStudentById(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {
		Student student = this.studentService.findStudentById(id);
		if (student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(student));
	}

	@ApiOperation(value = "Update Student", response = Student.class, httpMethod = "PUT", notes = "Update Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update Student", response = Student.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PutMapping("/updateStudent/{id}")
	@ResponseBody
	public ResponseEntity<Student> updateStudent(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id,
			@RequestBody Student student) {
		try {
			this.studentService.updateStudent(id, student);
			return ResponseEntity.ok().body(student);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Delete Student", response = Student.class, httpMethod = "DELETE", notes = "Delete Student")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Delete Student", response = Student.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@DeleteMapping("/deleteStudent/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteStudent(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {
		try {
			this.studentService.deleteStudent(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
