package com.advatix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advatix.entities.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
