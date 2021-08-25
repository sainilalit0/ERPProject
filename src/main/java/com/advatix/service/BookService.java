package com.advatix.service;

import java.util.List;

import com.advatix.entities.Book;

public interface BookService {

	List<Book> getAllBooks();

	Book findById(int id);

	Book addBook(Book book);

	void deleteBookId(int id);

	Book updateBook(int id, Book book);

}
