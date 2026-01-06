package net.javaguides.springboot_rest_api.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Student {
  private int id;
  private String firstName;
  private String lastName;
}
