package com.demo.learn.student.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.learn.student.entities.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer> {

}
