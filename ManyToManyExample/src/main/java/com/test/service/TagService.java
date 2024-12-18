package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Tag;
import com.test.repository.TagRepository;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTagById(Integer id) {
        return tagRepository.findById(id).orElse(null);
    }
}

