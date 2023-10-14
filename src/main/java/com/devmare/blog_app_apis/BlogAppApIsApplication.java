package com.devmare.blog_app_apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApIsApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApIsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("123456"));
        //? $2a$10$m4ePXfgEHAMbCktP/wmsoeGgbq57jDEqSHRi5GzUhx2QZhx8tE3RC
    }
}
