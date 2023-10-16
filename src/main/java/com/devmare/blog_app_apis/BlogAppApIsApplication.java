package com.devmare.blog_app_apis;

import com.devmare.blog_app_apis.configuration.AppConstants;
import com.devmare.blog_app_apis.entities.Role;
import com.devmare.blog_app_apis.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApIsApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApIsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(passwordEncoder.encode("123456"));
        //? $2a$10$m4ePXfgEHAMbCktP/wmsoeGgbq57jDEqSHRi5GzUhx2QZhx8tE3RC
        try {
            Role adminUser = new Role();
            adminUser.setId(AppConstants.ADMIN_USER);
            adminUser.setName("ROLE_ADMIN");

            Role normalUser = new Role();
            normalUser.setId(AppConstants.NORMAL_USER);
            normalUser.setName("ROLE_NORMAL");

            List<Role> roles = List.of(adminUser, normalUser);
            List<Role> result = roleRepository.saveAll(roles);
            result.forEach(role -> System.out.println(role.getName()));
        } catch (Exception e) {

        }
    }
}
