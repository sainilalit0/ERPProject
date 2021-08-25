package com.advatix.service;

import java.util.List;

import com.advatix.entities.Author;

public interface AuthorService {

	List<Author> getAllAuthor();

	Author findAuthorById(int id);

	Author AddAuthor(Author author);

	Author updateAuthor(int id, Author author);

	void deleteAuthor(int id);

}
