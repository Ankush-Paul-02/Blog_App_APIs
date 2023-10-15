package com.devmare.blog_app_apis.security;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtAuthenticationResponse {
    private String token;
}
