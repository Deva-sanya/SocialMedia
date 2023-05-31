package com.example.SocialMedia.repositories;

import com.example.SocialMedia.models.Friend;
import com.example.SocialMedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    boolean existsByFirstUserAndSecondUser(User first, User second);

    List<Friend> findByFirstUser(User user);
    List<Friend> findBySecondUser(User user);
}
