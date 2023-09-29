package com.devmare.blog_app_apis.payloads.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String imageName;
    private Date createdAt;
    private CategoryDTO category;
    private UserDTO user;
}
