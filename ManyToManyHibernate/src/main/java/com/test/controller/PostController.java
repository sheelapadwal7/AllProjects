package com.test.controller;

import com.test.model.Post;
import com.test.model.Tag;
import com.test.service.PostService;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/example")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/posts")
	public Post createPost(@RequestBody Post post, @RequestParam Set<Long> tagIds) {
		Set<Tag> tags = new HashSet<>();

		for (Long tagId : tagIds) {
			Tag tag = postService.getTagById(tagId);
			if (tag != null) {
				tags.add(tag);
			}
		}

		return postService.createPostWithTags(post, tags);
	}

	@PostMapping("/tags")
	public Tag createTag(@RequestBody Tag tag) {
		
		try {
			System.out.println(tag);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return postService.createTag(tag);
	}
}
