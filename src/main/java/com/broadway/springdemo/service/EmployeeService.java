package com.broadway.springdemo.service;

import com.broadway.springdemo.model.Employee;
import com.broadway.springdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(){

        return employeeRepository.findAll();

    }

}
