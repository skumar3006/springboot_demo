package com.demo.learn.user.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.demo.learn.exception.handler.ApplicationException;
import com.demo.learn.student.entities.Student;
import com.demo.learn.student.repositories.StudentRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public Student getStudent(Integer id) {

		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new EntityNotFoundException("Not found");
		}
		Student response = student.get();
		return response;

	}

	public List<Student> getStudents() {
		List<Student> students =  (List<Student>) studentRepository.findAll();
		Collections.sort(students);
		return students;
	}

	@Transactional(rollbackFor = { ApplicationException.class })
	public Student addStudentInDb(Student student) throws ApplicationException {
		Calendar calendar = Calendar.getInstance();
		student.setCreatedOn(calendar.getTimeInMillis());
		
		try {
			studentRepository.save(student);
		} catch (Exception e) {
			throw new ApplicationException("Error occurred while persisting data in DB");
		}


		return student;
	}

	@Transactional(rollbackFor = {Exception.class, IOException.class})
	public Student updateStudentInDb(Integer id, Student studentUpdate) {
		
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new EntityNotFoundException("Not found");
		}
		Student studentInDb = student.get();
		if (studentUpdate.getFirstName() != null) {
			studentInDb.setFirstName(studentUpdate.getFirstName());
		}
		if (studentUpdate.getLastName() != null) {
			studentInDb.setLastName(studentUpdate.getLastName());
		}
		if (studentUpdate.getEmail() != null) {
			studentInDb.setEmail(studentUpdate.getEmail());
		}
		studentRepository.save(studentInDb);
		return studentInDb;
	}

	public Boolean deleteStudentFromDb(Integer id) {
		
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new EntityNotFoundException("Not found");
		}
		studentRepository.deleteById(id);
		return true;
	}
	
	public static void main(String[] args) {
		
		Random r = new Random();
		System.out.println(r.nextInt(700));
	}

	public void processData() {

		List<Student> students = (List<Student>) studentRepository.findAll();
		log.info("Total Number of student found {}", students.size());
 		
	}

}
