package com.test.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Course;


public interface CourseRepository extends JpaRepository<Course,Integer> {

}
