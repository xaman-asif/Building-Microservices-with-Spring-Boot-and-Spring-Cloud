package net.javaguides.springboot_rest_api.controller;

import java.util.ArrayList;
import java.util.List;
import net.javaguides.springboot_rest_api.bean.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("student")
public class StudentController {

  // http://localhost:8080/student
  @GetMapping("student")
  public ResponseEntity<Student> getStudent() {
    Student student = new Student(
        1, "Asif", "Jaman"
    );

    return ResponseEntity.ok(student);
  }

  // http://localhost:8080/students
  @GetMapping()
  public ResponseEntity<List<Student>> getStudents() {

    List<Student> students = new ArrayList<>();

    students.add(new Student(
        1, "Ramesh", "Fadatare"
    ));

    students.add(new Student(
        2, "Umesh", "Fadatare"
    ));

    students.add(new Student(
        3, "Ram", "Jadhav"
    ));

    students.add(new Student(
        4, "Sanjay", "Pawar"
    ));

    return ResponseEntity.ok(students);
  }

  // http://localhost:8080/students/1/ramesh/fadatare
  @GetMapping("{id}/{first-name}/{last-name}")
  public ResponseEntity<Student> studentPathVariable(@PathVariable int id, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName) {
    return ResponseEntity.ok(new Student(id, "Asif", "Jaman"));
  }

  //  http://localhost:8080/students/query?id=1&firstName=Ramesh&lastName=Fadatare
  @GetMapping("query")
  public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName){
    return ResponseEntity.ok(new Student(id, firstName, lastName));
  }
}
