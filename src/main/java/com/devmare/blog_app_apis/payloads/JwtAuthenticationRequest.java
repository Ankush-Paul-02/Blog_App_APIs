package com.devmare.blog_app_apis.payloads;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}
