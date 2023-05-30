package com.example.SocialMedia.services;

import com.example.SocialMedia.models.Post;
import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.UsersRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    public User findUserById(int id) {
        Optional<User> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void saveUser(User user) {
        usersRepository.save(user);
    }


    @Transactional
    public void updateUser(int id, User updatedUser) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        usersRepository.deleteById(id);
    }

    public Optional<User> getUserByFullName(String fullName) {
        return usersRepository.findUserByFullName(fullName);
    }


    public List<Post> getPostByUserId(int id) {
        Optional<User> user = usersRepository.findById(id);

        if (user.isPresent()) {
            Hibernate.initialize(user.get().getPosts());
            return user.get().getPosts();
        } else {
            return Collections.emptyList();
        }
    }

}
