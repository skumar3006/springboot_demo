package com.demo.learn.student.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.learn.student.entities.Address;
import com.demo.learn.student.entities.Student;
import com.demo.learn.student.repositories.AddressRepository;
import com.demo.learn.user.service.StudentService;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	private StudentService studentService;
	
	public Address getAddress(Integer id) {
		Optional<Address> address =addressRepository.findById(id);
		return address.get();
	}

	public List<Address> getAllAddress(Integer studentId) {
		List<Address> list = addressRepository.findByStudentId(studentId);
		return list;
	}

	public Address updateAddressInDb(Integer id, Address address) {

		Optional<Address> addressInDb = addressRepository.findById(id);
		Address address2 = addressInDb.get();
		if (address.getAddress1() != null) {
			address2.setAddress1(address.getAddress1());
		}
		if (address.getAddress2() != null) {
			address2.setAddress1(address.getAddress2());
		}
		if (address.getCity() != null) {
			address2.setCity(address.getCity());
		}
		if (address.getState() != null) {
			address2.setState(address.getState());
		}
		if (address.getZip() != null) {
			address2.setZip(address.getZip());
		}
		addressRepository.save(address2);
		return address2;
	}

	public Boolean deleteAddressFromDb(Integer id) {
		addressRepository.deleteById(id);
		return true;
	}

	public void addStudentInDb(Integer studentId, @Valid Address address) {
		
		Student student = studentService.getStudent(studentId);
		Calendar calendar = Calendar.getInstance();
		address.setCreatedOn(calendar.getTimeInMillis());
		address.setStudent(student);
		addressRepository.save(address);
		
	}

}
