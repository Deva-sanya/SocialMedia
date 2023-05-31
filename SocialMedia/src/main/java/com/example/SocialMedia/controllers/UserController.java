package com.example.SocialMedia.controllers;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.PostRepository;
import com.example.SocialMedia.repositories.UsersRepository;
import com.example.SocialMedia.services.UsersService;
import com.example.SocialMedia.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersRepository usersRepository;
    private final PostRepository postRepository;
    private final UsersService usersService;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UsersRepository usersRepository, PostRepository postRepository, UsersService usersService, UserValidator userValidator) {
        this.usersRepository = usersRepository;
        this.postRepository = postRepository;
        this.usersService = usersService;
        this.userValidator = userValidator;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = usersService.findAllUsers();
        System.out.println(allUsers.toString());
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserInfo(User user) {
        user = usersService.getUserByEmail(user.getEmail());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/saveUser")
    public ResponseEntity<User> saveUserInfo(@ModelAttribute("user") @Valid User user) {
        usersService.saveUser(user);
        return ResponseEntity.ok(user);
    }

}
