package com.test.controller;


import com.test.model.Tag;
import com.test.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

  
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.saveTag(tag);
    }

 
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Integer id) {
        return tagService.getTagById(id);
    }

}

