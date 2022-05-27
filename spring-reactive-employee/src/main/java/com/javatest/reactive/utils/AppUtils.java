package com.javatest.reactive.utils;

import org.springframework.beans.BeanUtils;

import com.javatest.reactive.dto.EmployeeDto;
import com.javatest.reactive.entity.Employee;

public class AppUtils {


    public static EmployeeDto entityToDto(Employee product) {
    	EmployeeDto productDto = new EmployeeDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Employee dtoToEntity(EmployeeDto productDto) {
    	Employee product = new Employee();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}