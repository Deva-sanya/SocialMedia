package com.example.SocialMedia.controllers;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.PostRepository;
import com.example.SocialMedia.repositories.UsersRepository;
import com.example.SocialMedia.services.UsersService;
import com.example.SocialMedia.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
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


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        usersService.saveUser(user);
        return "redirect:/auth/login";
    }
}
