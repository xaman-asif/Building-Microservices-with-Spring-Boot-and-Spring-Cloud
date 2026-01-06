package net.javaguides.springboot_rest_api.service;

import java.util.List;
import net.javaguides.springboot_rest_api.dto.UserDto;
import net.javaguides.springboot_rest_api.entity.User;

public interface UserService {
  UserDto createUser(UserDto user);

  UserDto getUserById(Long userId);

  List<UserDto> getAllUsers();

  UserDto updateUser(UserDto user);

  void deleteById(Long userId);
}

