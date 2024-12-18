package com.test.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.Course;
import com.test.model.Registration;
import com.test.model.Student;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	List<Registration> findByStudent(Student student);

	Optional<Registration> findByStudentAndCourse(Student student, Course course);
}
