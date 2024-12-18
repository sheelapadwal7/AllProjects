package com.test.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
	
	Optional<Student> findById(Integer id);

}
