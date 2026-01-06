package net.javaguides.springboot_rest_api.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import net.javaguides.springboot_rest_api.dto.UserDto;
import net.javaguides.springboot_rest_api.entity.User;
import net.javaguides.springboot_rest_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
  private UserService userService;

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    UserDto savedUser = userService.createUser(userDto);

    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  @GetMapping("{userId}")
  public ResponseEntity<UserDto> getUserById(Long userId) {
    UserDto user = userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
    UserDto updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("{userId}")
  public  ResponseEntity<String> deleteUser(Long userId) {
    userService.deleteById(userId);
    return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
  }
}
