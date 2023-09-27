package com.devmare.blog_app_apis;

import com.devmare.blog_app_apis.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApIsApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void repoTest() {
        System.out.println("Package name: " + userRepository.getClass().getPackage());
        System.out.println("Clas name: " + userRepository.getClass().getName());
    }

}
