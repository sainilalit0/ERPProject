package com.advatix.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Student;
import com.advatix.exception.ResourceNotFoundException;
import com.advatix.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public Student addStudent(Student student) {
		if (Objects.isNull(student)) {
			throw new ResourceNotFoundException(" input fields are not empty " + student + "Bad Request ");
		}
		return this.studentRepository.save(student);

	}

	@Override
	@Transactional
	public List<Student> listOfstudents() {
		List<Student> listOfstudents = this.studentRepository.findAll();
		if (listOfstudents.isEmpty()) {
			throw new ResourceNotFoundException("while fetching all data from DB records not founds");
		}
		return listOfstudents;
	}

	@Override
	@Transactional
	public Student findStudentById(int id) {
		// return this.studentRepository.findById(id).orElseThrow();
		// another approach
		Student theStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with ID :" + id + " Not Found!"));
		return theStudent;
	}

	@Override
	@Transactional
	public void updateStudent(int id, Student student) {
		Student theStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with ID :" + id + " Not Found!"));
		student.setStudentId(theStudent.getStudentId());
		studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		Student theStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with ID :" + id + " Not Found!"));
		studentRepository.deleteById(theStudent.getStudentId());
	}

}
