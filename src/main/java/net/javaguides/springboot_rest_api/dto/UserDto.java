package net.javaguides.springboot_rest_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty(message = "User email shouldn't be null or empty")
  @Email(message = "Email address should be valid")
  @Size(min = 5, message = "Email shouldn't be minimum 5 characters")
  private String email;
}
