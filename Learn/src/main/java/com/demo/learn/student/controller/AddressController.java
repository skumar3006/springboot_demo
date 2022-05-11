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

import com.demo.learn.student.entities.Address;
import com.demo.learn.student.service.AddressService;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/{studentId}")
	public ResponseEntity<Address> addStudent(@Valid @RequestBody Address address,
			@PathVariable("studentId") Integer studentId) {
		 addressService.addStudentInDb(studentId, address);
		 return new ResponseEntity<Address>(address, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> getStudentById(@PathVariable("id") Integer id) {
		Address address = addressService.getAddress(id);
		
		return ResponseEntity.ok().body(address);
	}
	
	@GetMapping
	public List<Address> getAddresses(@PathVariable("studentId") Integer studentId) {
		return addressService.getAllAddress(studentId);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable("id") Integer id, @RequestBody Address address) {
		Address response = addressService.updateAddressInDb(id, address);
		return new ResponseEntity<Address>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Integer id) {
			Boolean status = addressService.deleteAddressFromDb(id);
			return ResponseEntity.ok(status);
	}

}
