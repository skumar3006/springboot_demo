package com.demo.learn.student.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.learn.student.entities.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {

	List<Address> findByStudentId(Integer studentId);

}
