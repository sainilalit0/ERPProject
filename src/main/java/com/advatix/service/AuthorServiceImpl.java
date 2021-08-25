package com.advatix.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Author;
import com.advatix.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
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
		Optional<Author> result=authorRepository.findById(id);
		Author author=null;
		try {
			if(result.isPresent()) {
				author=result.get();
			}
			else {
				throw new RuntimeException(" Author is is not found "+id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return author;
	}

	@Override
	@Transactional
	public Author AddAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	@Transactional
	public Author updateAuthor(int id, Author author) {
		author.setId(id);
		return authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(int id) {
		authorRepository.deleteById(id);
	}
	
	

}
