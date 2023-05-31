package com.example.SocialMedia.security;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("email Not found" + email);
        }
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
