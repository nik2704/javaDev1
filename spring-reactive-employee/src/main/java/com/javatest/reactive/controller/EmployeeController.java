package com.javatest.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.javatest.reactive.dto.EmployeeDto;
import com.javatest.reactive.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public Flux<EmployeeDto> getEmployees(){
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeDto> getProduct(@PathVariable String id){
        return service.getEmployee(id);
    }

    @PostMapping
    public Mono<EmployeeDto> saveProduct(@RequestBody Mono<EmployeeDto> employeeDtoMono){
    	System.out.println("controller method called ...");
        return service.saveEmployee(employeeDtoMono);
    }
    
    @PutMapping("/update/{id}")
    public Mono<EmployeeDto> updateProduct(@RequestBody Mono<EmployeeDto> employeeDtoMono,@PathVariable String id){
        return service.updateEmployee(employeeDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return service.deleteEmployee(id);
    }

}