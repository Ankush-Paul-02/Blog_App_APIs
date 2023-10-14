package com.devmare.blog_app_apis.security;

import com.devmare.blog_app_apis.exceptions.ResourceNotFoundException;
import com.devmare.blog_app_apis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //! Load user form db by username and return user details
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User ", "email: " + username, 0));
    }
}
