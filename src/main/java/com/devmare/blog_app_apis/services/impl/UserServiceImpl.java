package com.devmare.blog_app_apis.services.impl;

import com.devmare.blog_app_apis.configuration.ModelMapperConfiguration;
import com.devmare.blog_app_apis.entities.User;
import com.devmare.blog_app_apis.exceptions.ResourceNotFoundException;
import com.devmare.blog_app_apis.payloads.dto.UserDTO;
import com.devmare.blog_app_apis.repositories.UserRepository;
import com.devmare.blog_app_apis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperConfiguration modelMapperConfiguration;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User ", "id: ", userId)
        );
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User updatedUser = userRepository.save(user);
        return userToUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User ", "id: ", userId)
        );
        return userToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> userToUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User ", "id: ", userId)
        );
        userRepository.delete(user);
    }

    public User dtoToUser(UserDTO userDTO) {
        return modelMapperConfiguration.modelMapper().map(userDTO, User.class);
    }

    public UserDTO userToUserDTO(User user) {
        return modelMapperConfiguration.modelMapper().map(user, UserDTO.class);
    }
}
