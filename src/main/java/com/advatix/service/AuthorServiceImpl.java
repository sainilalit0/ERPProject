package com.advatix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Author;
import com.advatix.exception.ResourceNotFoundException;
import com.advatix.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	@Transactional
	public List<Author> getAllAuthor() {
		return authorRepository.findAll();
	}

	@Override
	@Transactional
	public Author findAuthorById(int id) {
		Author theAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author with ID :" + id + " Not Found!"));
		return theAuthor;
	}

	@Override
	@Transactional
	public Author AddAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	@Transactional
	public Author updateAuthor(int id, Author author) {
		Author theAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author with ID :" + id + " Not Found!"));
		author.setId(theAuthor.getId());
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(int id) {
		Author theAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author with ID :" + id + " Not Found!"));
		authorRepository.deleteById(theAuthor.getId());
	}

}
