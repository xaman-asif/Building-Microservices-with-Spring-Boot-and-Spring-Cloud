package com.practice.employee.service;

import com.practice.employee.dto.OrganizationDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = modelMapper.map(employeeDto, Employee.class);
    Employee savedEmployee = employeeRepository.save(employee);
    return modelMapper.map(savedEmployee, EmployeeDto.class);
  }

  //    @CircuitBreaker(name = "${spring.application.name}",
//        fallbackMethod = "getDefaultDepartment"
//    )
  @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Override
  public EmployeeDto getEmployeeById(Long id) {

    logger.info("inside getEmployeeById() method");
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

    ResponseEntity<DepartmentDto> departmentResponse = apiClient.getDepartmentByCode(employee.getDepartmentCode());
    OrganizationDto organizationDto = webClient.get()
        .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode()).retrieve()
        .bodyToMono(OrganizationDto.class).block();

    DepartmentDto departmentDto = departmentResponse.getBody();

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    employeeDto.setDepartmentDto(departmentDto);
    employeeDto.setOrganizationDto(organizationDto);

    return employeeDto;
  }

  public EmployeeDto getDefaultDepartment(Long id, Throwable throwable) {
    logger.info("inside getDefaultDepartment() method");
    Employee employee = employeeRepository.findById(id).get();

    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("R&D");
    departmentDto.setDepartmentCode("RD001");
    departmentDto.setDepartmentDescription("Research and Development Department");

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

    employeeDto.setDepartmentDto(departmentDto);

    return employeeDto;
  }
}
