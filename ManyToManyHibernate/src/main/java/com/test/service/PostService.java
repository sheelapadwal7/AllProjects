package com.test.service;

import com.test.model.Post;
import com.test.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PostService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Post createPostWithTags(Post post, Set<Tag> tags) {
		Session session = sessionFactory.getCurrentSession();
		try {

			post.setTags(tags);

			session.save(post);

			return post;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Tag createTag(Tag tag) {
		Session session = sessionFactory.getCurrentSession();
		try {

			session.save(tag);
			return tag;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	@Transactional
	public Tag getTagById(Long tagId) {
		Session session = sessionFactory.getCurrentSession();
		try {

			return session.get(Tag.class, tagId);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
}
