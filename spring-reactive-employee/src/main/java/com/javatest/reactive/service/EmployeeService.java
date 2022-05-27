package com.javatest.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.reactive.dto.EmployeeDto;
import com.javatest.reactive.repository.EmployeeRepository;
import com.javatest.reactive.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    public Flux<EmployeeDto> getEmployees(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> getEmployee(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> saveEmployee(Mono<EmployeeDto> employeeDtoMono){
      return  employeeDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<EmployeeDto> updateEmployee(Mono<EmployeeDto> employeeDtoMono, String id){
        return repository.findById(id)
                 .flatMap(p->employeeDtoMono.map(AppUtils::dtoToEntity)
                 .doOnNext(e->e.setId(id)))
                 .flatMap(repository::save)
                 .map(AppUtils::entityToDto);
     }

    public Mono<Void> deleteEmployee(String id){
        return repository.deleteById(id);
    }
}