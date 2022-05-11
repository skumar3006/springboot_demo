package com.demo.learn.student.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
@EqualsAndHashCode(exclude = { "address"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Student implements Comparable<Student>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 22L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "First Name may not be null")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull(message = "Last Name may not be null")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "Email may not be null")
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "created_on")
	private Long createdOn;

	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private Set<Address> address;
	
	@Override
	public int compareTo(Student o) {
		return this.firstName.compareTo(o.firstName);
	}
	
	public Student(String firstName, String lastName, Integer id) {
		this.firstName= firstName;
		this.lastName= lastName;
		this.id= id;
		
		
	}
}
