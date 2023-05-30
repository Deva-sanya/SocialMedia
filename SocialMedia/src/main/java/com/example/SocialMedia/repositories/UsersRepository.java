package com.example.SocialMedia.repositories;

import com.example.SocialMedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByFullName(String fullName);
}
