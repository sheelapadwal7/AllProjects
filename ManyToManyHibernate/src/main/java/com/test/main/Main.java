package com.test.main;

import com.test.model.Post;
import com.test.model.Tag;
import com.test.repository.PostRepository;
import com.test.repository.TagRepository;

public class Main {
    public static void main(String[] args) {
        
        PostRepository postRepository = new PostRepository() {}; 
        TagRepository tagRepository = new TagRepository() {}; 

       
        Tag tag1 = new Tag();
        tag1.setName("Java");

        Tag tag2 = new Tag();
        tag2.setName("Spring");

        
        tagRepository.saveTag(tag1);
        tagRepository.saveTag(tag2);

     
        Post post = new Post();
        post.setTitle("Spring Boot and Hibernate");
        post.setContent("Learning Spring Boot with Hibernate for a web app.");
        
     
        post.addTag(tag1);
        post.addTag(tag2);

       
        postRepository.savePost(post);

       
        System.out.println("Posts: " + postRepository.getAllPosts());
        System.out.println("Tags: " + tagRepository.getAllTags());
        
       
        postRepository.close();
        tagRepository.close();
    }
}
