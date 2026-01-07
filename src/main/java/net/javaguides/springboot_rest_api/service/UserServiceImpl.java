package net.javaguides.springboot_rest_api.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import net.javaguides.springboot_rest_api.dto.UserDto;
import net.javaguides.springboot_rest_api.entity.User;
import net.javaguides.springboot_rest_api.exception.EmailAlreadyExistsException;
import net.javaguides.springboot_rest_api.exception.ResourceNotFoundException;
import net.javaguides.springboot_rest_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  @Override
  public UserDto createUser(UserDto userDto) {

    Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

    if (optionalUser.isPresent()) {
      throw new EmailAlreadyExistsException(userDto.getEmail());
    }

    User user = modelMapper.map(userDto, User.class);

    user = userRepository.save(user);

    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public UserDto getUserById(Long userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    return modelMapper.map(user, UserDto.class);
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
  }

  @Override
  public UserDto updateUser(UserDto userDto) {
    User existingUser =
        userRepository.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id",
            userDto.getId()));
    existingUser.setFirstName(userDto.getFirstName());
    existingUser.setLastName(userDto.getLastName());
    existingUser.setEmail(userDto.getEmail());

    return modelMapper.map(existingUser, UserDto.class);
  }

  @Override
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }
}
