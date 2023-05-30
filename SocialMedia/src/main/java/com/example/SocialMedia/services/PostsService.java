package com.example.SocialMedia.services;

import com.example.SocialMedia.models.Post;
import com.example.SocialMedia.models.User;
import com.example.SocialMedia.repositories.PostRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PostsService {

    private final PostRepository postRepository;

    @Autowired
    public PostsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll(PageRequest.of(0, 10, Sort.by("createdAt"))).getContent();
    }

    public Post findPostById(int id) {
        Optional<Post> foundPost = postRepository.findById(id);
        return foundPost.orElse(null);
    }

    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(int id, Post updatedPost) {
        updatedPost.setId(id);
        postRepository.save(updatedPost);
    }

    @Transactional
    public void delete(int id) {
        postRepository.deleteById(id);
    }

    public User getPostOwner(int id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Hibernate.initialize(post.get().getOwner());
            return post.get().getOwner();
        } else {
            return (User) Collections.emptyList();
        }
    }
}
