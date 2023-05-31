package com.example.SocialMedia.controllers;

import com.example.SocialMedia.models.User;
import com.example.SocialMedia.security.SecurityService;
import com.example.SocialMedia.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendsService friendsService;
    private final SecurityService securityService;

    @Autowired
    public FriendController(FriendsService friendsService, SecurityService securityService) {
        this.friendsService = friendsService;
        this.securityService = securityService;
    }


    @GetMapping("/addFriend")
    public ResponseEntity<?> addUser(@RequestParam("friendId") String friendId) throws NullPointerException {
        UserDto currentUser = securityService.getUser();
        friendsService.saveFriend(currentUser, Integer.parseInt(friendId));
        return ResponseEntity.ok("Friend added successfully");
    }

    @GetMapping("/listFriends")
    public ResponseEntity<List<User>> getFriends() {
        List<User> myFriends = friendsService.getFriends();
        return new ResponseEntity<List<User>>(myFriends, HttpStatus.OK);
    }
}
