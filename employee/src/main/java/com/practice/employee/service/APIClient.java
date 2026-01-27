package com.practice.employee.service;

import com.practice.employee.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.employee.dto.DepartmentDto;

@FeignClient(name = "DEPARTMENT")
public interface APIClient {

  @GetMapping("api/departments/{department-code}")
  public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}
