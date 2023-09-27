package com.devmare.blog_app_apis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
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
}
