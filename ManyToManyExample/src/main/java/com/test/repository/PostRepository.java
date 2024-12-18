package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
