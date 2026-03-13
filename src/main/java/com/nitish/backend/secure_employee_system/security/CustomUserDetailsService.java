package com.nitish.backend.secure_employee_system.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                "admin",
                "$2a$10$npzaua4zSsShWNYeBRXgBecdD7t.DirwZPQa8UHKkjDuMHqZbWfOK",
                Collections.emptyList()
        );
    }
}