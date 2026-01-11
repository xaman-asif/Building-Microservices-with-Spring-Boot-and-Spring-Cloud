package com.practice.department.controller;

import org.springframework.stereotype.Controller;

import com.practice.department.service.DepartmentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    
}
