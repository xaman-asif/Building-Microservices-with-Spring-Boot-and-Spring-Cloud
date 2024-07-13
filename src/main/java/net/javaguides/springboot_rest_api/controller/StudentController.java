package net.javaguides.springboot_rest_api.controller;

import net.javaguides.springboot_rest_api.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  @GetMapping("student")
  public Student getStudent() {
    Student student = new Student(
      1, "Asif", "Jaman"
    );

    return student;
  }
}
