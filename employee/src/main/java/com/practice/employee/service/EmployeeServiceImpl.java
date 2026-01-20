package com.practice.employee.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.practice.employee.dto.DepartmentDto;
import com.practice.employee.dto.EmployeeDto;
import com.practice.employee.entity.Employee;
import com.practice.employee.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @CircuitBreaker(name = "${spring.application.name",
        fallbackMethod = "getDefaultDepartment"
    )
    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();

        // ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
        // "http://localhost:8081/api/departments/" + employee.getDepartmentCode(),
        // DepartmentDto.class);

        // DepartmentDto departmentDto = responseEntity.getBody();

        // DepartmentDto departmentDto = webClient.get()
        // .uri("http://localhost:8081/api/departments/" +
        // employee.getDepartmentCode()).retrieve()
        // .bodyToMono(DepartmentDto.class)
        // .block();

        ResponseEntity<DepartmentDto> responseEntity = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        employeeDto.setDepartmentDto(departmentDto);

        return employeeDto;
    }
}
