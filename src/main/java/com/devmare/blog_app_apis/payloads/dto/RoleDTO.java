package com.devmare.blog_app_apis.payloads.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RoleDTO {
    @Id
    private Integer id;
    private String name;
}
