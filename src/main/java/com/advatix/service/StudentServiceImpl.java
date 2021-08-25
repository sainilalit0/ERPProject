package com.advatix.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advatix.entities.Student;
import com.advatix.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public Student addStudent(Student student) {
		return this.studentRepository.save(student);
	}

	@Override
	@Transactional
	public List<Student> listOfstudents() {
		return this.studentRepository.findAll();
	}

	@Override
	@Transactional
	public Student findStudentById(int id) {
		//return this.studentRepository.findById(id).orElseThrow();
		// another approach
		Optional<Student> result=this.studentRepository.findById(id);
		Student student=null;
		try {
			if(result.isPresent()) {
				student=result.get();
			}
			else {
				throw new RuntimeException(" student id is not found "+id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	@Transactional
	public void updateStudent(int id, Student student) {
		student.setStudentId(id);
		studentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
	studentRepository.deleteById(id);	
	}

}
