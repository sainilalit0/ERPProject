package com.advatix.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import com.advatix.entities.Book;
import com.advatix.enums.DeviceType;
import com.advatix.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = Constant.API_BOOK)
@Api(value = Constant.BOOK_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, tags = {
		"Book Api's" }, hidden = false)
public class BookController {

	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Get List Book", response = Book.class, httpMethod = "GET", notes = "Get List Book")	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get List Book", response = Book.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@GetMapping("/getAllBooks")
	@ResponseBody
	public ResponseEntity<List<Book>> getAllBooks(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion) {
		List<Book> bookList = this.bookService.getAllBooks();
		if (bookList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(bookList));
		}
	}

	@ApiOperation(value = "Get Single Book", response = Book.class, httpMethod = "GET", notes = "Get Single Book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get single Book", response = Book.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@GetMapping("/getBookById")
	@ResponseBody
	public ResponseEntity<Book> getBookById(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathParam("id") int id) {
		Book book = this.bookService.findById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	@ApiOperation(value = "Add Book", response = Book.class, httpMethod = "POST", notes = "Add Book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Add Book", response = Book.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@PostMapping("/AddBook")
	@ResponseBody
	public ResponseEntity<Book> addBook(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @Valid @RequestBody Book book) {
		Book bookObject = null;
		try {
			bookObject = this.bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@ApiOperation(value = "Delete Book", response = Book.class, httpMethod = "DELETE", notes = "Delete Book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Delete Book", response = Book.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@DeleteMapping("/deleteBook/{id}")
	@ResponseBody
	public ResponseEntity<String> deleteBook(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id) {

		try {
			this.bookService.deleteBookId(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Update Book", response = Book.class, httpMethod = "PUT", notes = "Update Book")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update Book", response = Book.class),
			@ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 401, message = "Not Authorized")})
	@PutMapping("/updateBook/{id}")
	@ResponseBody
	public ResponseEntity<Book> updateBook(@RequestHeader(name = Constant.DEVICE_TYPE) DeviceType deviceType,
			@RequestHeader(name = Constant.APP_VERSION) String appVersion, @PathVariable int id,
			@RequestBody Book book) {

		try {
			this.bookService.updateBook(id, book);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
