package com.devmare.blog_app_apis.payloads.dto;

import com.devmare.blog_app_apis.entities.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be contain atleast 4 characters!")
    private String name;

    @Email(message="Email address is not valid!")
    private String email;

    @NotEmpty
    @Size(min = 6, message = "Password must be contain atleast 6-10 characters!", max = 10)
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDTO> roles = new HashSet<>();
}
