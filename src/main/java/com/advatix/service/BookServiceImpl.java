package com.advatix.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Book;
import com.advatix.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	@Transactional
	public List<Book> getAllBooks() {

		return bookRepository.findAll();
	}

	@Override
	@Transactional
	public Book findById(int id) {
		Optional<Book> result = bookRepository.findById(id);
		Book book = null;
		try {
			if (result.isPresent()) {
				book = result.get();
			} else {
				throw new RuntimeException(" Book id is not found" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	@Transactional
	public Book addBook(Book book) {

		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteBookId(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Book updateBook(int id, Book book) {
		book.setId(id);
		return bookRepository.save(book);

	}

}
