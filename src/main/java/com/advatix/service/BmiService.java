package com.advatix.service;

import java.util.List;

import com.advatix.entities.Bmi;

public interface BmiService {

	Bmi calculateBmi(Bmi bmi);

	List<Bmi> listOfBmi();

}
