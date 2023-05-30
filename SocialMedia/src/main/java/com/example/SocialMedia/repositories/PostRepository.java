package com.example.SocialMedia.repositories;

import com.example.SocialMedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
