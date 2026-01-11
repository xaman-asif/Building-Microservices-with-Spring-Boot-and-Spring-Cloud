package com.practice.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  Department findByDepartmentCode(String code);
}
