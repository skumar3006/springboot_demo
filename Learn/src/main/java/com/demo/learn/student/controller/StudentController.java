package com.demo.learn.student.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.learn.exception.handler.ApplicationException;
import com.demo.learn.student.entities.Student;
import com.demo.learn.user.service.StudentService;

@RestController
@RequestMapping("/v1/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
		Student student = studentService.getStudent(id);
		
		return ResponseEntity.ok().body(student);
	}
	
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) throws ApplicationException {
		 studentService.addStudentInDb(student);
		 return new ResponseEntity<Student>(student, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Integer id, @RequestBody Student student) {
		Student response = studentService.updateStudentInDb(id, student);
		return new ResponseEntity<Student>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Integer id) {
			Boolean status = studentService.deleteStudentFromDb(id);
			return ResponseEntity.ok(status);
	}

}
