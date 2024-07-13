package net.javaguides.springboot_rest_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping("/hello-world")
  public String helloWorld() {
    return "Hello World";
  }
}
