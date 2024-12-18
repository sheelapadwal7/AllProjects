package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.model.Post;
import com.test.model.Tag;
import com.test.service.PostService;
import com.test.service.TagService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);

        if (post.isPresent()) {
            return ResponseEntity.ok(post.get()); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found with this :"+id); 
        }
    }

    @PostMapping("/{postId}/tags/{tagId}")
    public ResponseEntity<?> addTagToPost(@PathVariable Integer postId, @PathVariable Integer tagId) {
        Post post = postService.getPostByIds(postId);
        Tag tag = tagService.getTagById(tagId);

        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Post with id " + postId + " not found.");
        }

        if (tag == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tag with id " + tagId + " not found.");
        }

      
        Set<Tag> tags = post.getTags();
        if (tags == null) {
            tags = new HashSet<>();
        }
        tags.add(tag);
        post.setTags(tags);

        
        Post updatedPost = postService.savePost(post);
        
        
        return ResponseEntity.ok(updatedPost);
    }

}

