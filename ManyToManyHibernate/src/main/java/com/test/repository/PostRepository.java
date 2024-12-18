package com.test.repository;

import com.test.model.Post;
import com.test.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface PostRepository {
    
    // The SessionFactory should still be static as it's tied to the implementation
    static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml") // If you have Hibernate configuration XML
            .addAnnotatedClass(Post.class)
            .addAnnotatedClass(Tag.class)
            .buildSessionFactory();

    // Default method to save a post
    default void savePost(Post post) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(post);
            session.getTransaction().commit();
        } finally {
            // Do not close factory here as it is shared across all repository instances
            // closing should be done at application shutdown
        }
    }

    // Default method to retrieve all posts
    default List<Post> getAllPosts() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        CriteriaQuery<Post> criteriaQuery = session.getCriteriaBuilder().createQuery(Post.class);
        criteriaQuery.from(Post.class);
        List<Post> posts = session.createQuery(criteriaQuery).getResultList();

        session.getTransaction().commit();
        return posts;
    }

    // Optionally, you can add a default method for closing the factory (this should be used at app shutdown)
    default void close() {
        factory.close();
    }
}
