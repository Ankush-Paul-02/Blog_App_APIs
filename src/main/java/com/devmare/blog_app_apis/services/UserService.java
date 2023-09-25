package com.devmare.blog_app_apis.services;

import com.devmare.blog_app_apis.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);
}
