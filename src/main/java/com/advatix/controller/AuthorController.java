package com.advatix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.advatix.commons.utils.Constant;
import com.advatix.entities.Author;
import com.advatix.enums.DeviceType;
import com.advatix.service.AuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_AUTHOR)
@Api(value = Constant.AUTHOR_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"Authors Api's" }, hidden = false)
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@ApiOperation(value = "Get Author", response = Author.class, httpMethod = "GET", notes = "Get All Author")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/listOfAuthors")
	@ResponseBody
	public ResponseEntity<List<Author>> getAllAuthor(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion) {
		List<Author> authorList = this.authorService.getAllAuthor();
		if (authorList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(authorList));
		}
	}

	@ApiOperation(value = "Get Author", response = Author.class, httpMethod = "GET", notes = "Find By Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Find By Id", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@GetMapping("/findAuthorById/{id}")
	@ResponseBody
	public ResponseEntity<Author> findAuthorById(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable @Min(1) int id) {
		Author author = this.authorService.findAuthorById(id);		
		return ResponseEntity.ok().body(author);
	}

	@ApiOperation(value = "Add Author", response = Author.class, httpMethod = "POST", notes = "Add Author")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Add Author", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PostMapping("/addAuthor")
	@ResponseBody
	public ResponseEntity<Author> AddAuthor(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @RequestBody Author author) {
		Author authorObject = new Author();

		try {
			authorObject = this.authorService.AddAuthor(author);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(authorObject.getId()).toUri();
			return ResponseEntity.created(location).body(author);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Update Author", response = Author.class, httpMethod = "PUT", notes = "Update Author")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update Author", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@PutMapping("/updateAuthor/{id}")
	@ResponseBody
	public ResponseEntity<Author> updateAuthor(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id,
			@RequestBody Author author) {		
			this.authorService.updateAuthor(id, author);
			return ResponseEntity.ok().body(author);
		
	}

	@ApiOperation(value = "Delete Author", response = Author.class, httpMethod = "DELETE", notes = "Delete Author")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Delete Author", response = Author.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not found"), @ApiResponse(code = 401, message = "Not Authorized") })
	@DeleteMapping("/deleteAuthor/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteAuthor(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {
		
			this.authorService.deleteAuthor(id);
			return ResponseEntity.ok().body("Author deleted with success!");		
	}

}
