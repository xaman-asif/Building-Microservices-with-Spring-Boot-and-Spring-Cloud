package com.practice.department.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.practice.department.dto.DepartmentDto;
import com.practice.department.entity.Department;
import com.practice.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);
        return modelMapper.map(department, DepartmentDto.class);
    }
}
