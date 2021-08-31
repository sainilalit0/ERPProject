package com.advatix.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "student")
@ApiModel(description = " All attribute of Student data ")
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097862231528624662L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "autoGeneratedId", hidden = true)
	@Column(name = "student_id")
	private int studentId;

	@ApiModelProperty(notes = "first name at least 2 character", required = true, position = 1)
	@NotEmpty
	@Column(name = "first_name")
	private String firstName;

	@ApiModelProperty(notes = "last name at least 2 character", required = true, position = 2)
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;

	@ApiModelProperty(notes = "phone no at least 2 character", required = true, position = 3)
	@NotEmpty
	@Column(name = "phone")
	private String phone;

	@ApiModelProperty(notes = "email at least 2 character", required = true, position = 4)
	@Email(message = "email cannot be empty")
	@Column(name = "email")
	private String email;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "author reference", required = true, position = 5)
	@JsonManagedReference
	private Course course;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "author reference", required = true, position = 6)
	@JsonManagedReference
	private Address address;

	public Student() {
	}

	public Student(String firstName, String lastName, String phone, String email, Course course, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.course = course;
		this.address = address;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ phone + ", email=" + email + ", course=" + course + ", address=" + address + "]";
	}

}
