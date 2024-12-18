package com.test.repository;

import com.test.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface TagRepository {

    // The SessionFactory should be shared across all operations
    static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Tag.class)
            .buildSessionFactory();

    // Default method to save a tag
    default void saveTag(Tag tag) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(tag);
            session.getTransaction().commit();
        } finally {
            // Don't close the factory here, it should be managed at app shutdown
        }
    }

    // Default method to retrieve all tags
    default List<Tag> getAllTags() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        CriteriaQuery<Tag> criteriaQuery = session.getCriteriaBuilder().createQuery(Tag.class);
        criteriaQuery.from(Tag.class);
        List<Tag> tags = session.createQuery(criteriaQuery).getResultList();

        session.getTransaction().commit();
        return tags;
    }

    // Default method to close the factory (app shutdown)
    default void close() {
        factory.close();
    }
}
