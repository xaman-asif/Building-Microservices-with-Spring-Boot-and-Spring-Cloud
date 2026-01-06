package net.javaguides.springboot_rest_api.mapper;

import net.javaguides.springboot_rest_api.dto.UserDto;
import net.javaguides.springboot_rest_api.entity.User;

public class UserMapper {
  public static UserDto mapUserToUserDto(User user) {
    return new UserDto(user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail());
  }

  public static User mapUserDtoToUser(UserDto userDto) {
    return new User(userDto.getId(),
        userDto.getFirstName(),
        userDto.getLastName(),
        userDto.getEmail());
  }
}
