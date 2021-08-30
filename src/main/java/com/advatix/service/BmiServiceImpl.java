package com.advatix.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Bmi;
import com.advatix.repository.BmiRepository;

@Service
public class BmiServiceImpl implements BmiService{

	@Autowired
	private BmiRepository bmiRepository;

	@Override
	@Transactional
	public Bmi calculateBmi(Bmi bmi) {
		float weight= bmi.getWeight();
		float height=bmi.getHeight();
		float bmiCal=weight / (height * height);
		bmi.setResult(bmiCal);
		bmiRepository.save(bmi);
		return bmi;
	}

	@Override
	public List<Bmi> listOfBmi() {
		return bmiRepository.findAll();
	}
}
