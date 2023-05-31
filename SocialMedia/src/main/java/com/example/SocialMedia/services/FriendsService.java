package com.example.SocialMedia.services;

import com.example.SocialMedia.models.Friend;
import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.FriendRepository;
import com.example.SocialMedia.repositories.UsersRepository;
import com.example.SocialMedia.security.SecurityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FriendsService {
    private final FriendRepository friendRepository;
    private final UsersRepository usersRepository;
    private final SecurityService securityService;

    public FriendsService(FriendRepository friendRepository, UsersRepository usersRepository, SecurityService securityService) {
        this.friendRepository = friendRepository;
        this.usersRepository = usersRepository;
        this.securityService = securityService;
    }

    public void saveFriend(User user1, int id) throws NullPointerException {

        Optional<User> user = usersRepository.findById(id);
        User user2 = null;

        Friend friend = new Friend();
        user1 = usersRepository.findUserByEmail(user1.getEmail());

        if (user.isPresent()) {
            user2 = usersRepository.findUserByEmail(user.get().getEmail());
        }

        User firstUser = user1;
        User secondUser = user2;
        if (user1.getId() > user2.getId()) {
            firstUser = user2;
            secondUser = user1;
        }
        if (!(friendRepository.existsByFirstUserAndSecondUser(firstUser, secondUser))) {
            friend.setCreatedDate(new Date());
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            friendRepository.save(friend);
        }
    }

    public List<User> getFriends() {

        UserDto currentUserDto = usersRepository.getUser();
        User currentUser = usersRepository.findUserByEmail(currentUserDto.getEmail());
        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();

        /*
            suppose there are 3 users with id 1,2,3.
            if user1 add user2 as friend database record will be first user = user1 second user = user2
            if user3 add user2 as friend database record will be first user = user2 second user = user3
            it is because of lexicographical order
            while calling get friends of user 2 we need to check as a both first user and the second user
         */
        for (Friend friend : friendsByFirstUser) {
            friendUsers.add(usersRepository.findUserById(friend.getSecondUser().getId()));
        }
        for (Friend friend : friendsBySecondUser) {
            friendUsers.add(usersRepository.findUserById(friend.getFirstUser().getId()));
        }
        return friendUsers;

    }
}
