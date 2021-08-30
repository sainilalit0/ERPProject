package com.advatix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advatix.entities.Bmi;

@Repository
public interface BmiRepository extends JpaRepository<Bmi, Integer> {

}
