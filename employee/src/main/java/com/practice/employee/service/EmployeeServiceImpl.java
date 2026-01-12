package com.practice.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.employee.dto.EmployeeDto;
import com.practice.employee.entity.Employee;
import com.practice.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return modelMapper.map(employee, EmployeeDto.class);
    }

}
