package com.advatix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Book;
import com.advatix.exception.ResourceNotFoundException;
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
		Book thBook = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with ID :" + id + " Not Found!"));
		return thBook;
	}

	@Override
	@Transactional
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteBookId(int id) {
		Book thBook = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with ID :" + id + " Not Found!"));
		bookRepository.deleteById(thBook.getId());
	}

	@Override
	@Transactional
	public Book updateBook(int id, Book book) {
		Book thBook = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book with ID :" + id + " Not Found!"));
		book.setId(thBook.getId());
		return bookRepository.save(book);

	}

}
