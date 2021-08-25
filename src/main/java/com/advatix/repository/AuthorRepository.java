package com.advatix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advatix.entities.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
