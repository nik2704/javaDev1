package com.javatest.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.javatest.reactive.entity.Employee;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}
