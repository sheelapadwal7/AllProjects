package com.test.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Post;
import com.test.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostByIds(Integer id) {
        return postRepository.findById(id).orElse(null);
    }
    
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id); 
    }
}

