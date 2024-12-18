package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer> {

}
