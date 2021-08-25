package com.advatix.service;

import java.util.List;

import com.advatix.entities.Student;

public interface StudentService {

	Student addStudent(Student student);

	List<Student> listOfstudents();

	Student findStudentById(int id);

	void updateStudent(int id, Student student);

	void deleteStudent(int id);

}
