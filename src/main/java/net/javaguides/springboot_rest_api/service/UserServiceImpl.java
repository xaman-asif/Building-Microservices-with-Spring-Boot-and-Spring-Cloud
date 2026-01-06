package net.javaguides.springboot_rest_api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import net.javaguides.springboot_rest_api.dto.UserDto;
import net.javaguides.springboot_rest_api.entity.User;
import net.javaguides.springboot_rest_api.mapper.UserMapper;
import net.javaguides.springboot_rest_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto userDto) {
    User user = UserMapper.mapUserDtoToUser(userDto);
    user = userRepository.save(user);
    return UserMapper.mapUserToUserDto(user);
  }

  @Override
  public UserDto getUserById(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    User user = optionalUser.get();
    return UserMapper.mapUserToUserDto(user);
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(UserMapper::mapUserToUserDto).toList();
  }

  @Override
  public UserDto updateUser(UserDto userDto) {
    User existingUser = userRepository.findById(userDto.getId()).get();
    existingUser.setFirstName(userDto.getFirstName());
    existingUser.setLastName(userDto.getLastName());
    existingUser.setEmail(userDto.getEmail());
    User savedUser = userRepository.save(existingUser);
    return UserMapper.mapUserToUserDto(savedUser);
  }

  @Override
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }
}
