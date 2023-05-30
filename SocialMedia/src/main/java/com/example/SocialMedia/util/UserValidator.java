package com.example.SocialMedia.util;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UsersService usersService;

    @Autowired
    public UserValidator(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (usersService.getUserByFullName(user.getFullName()).isPresent())
            errors.rejectValue("username", "", "Person with this username already exists");
    }
}
