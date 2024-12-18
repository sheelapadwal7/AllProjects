package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Course;
import com.test.model.Registration;
import com.test.model.Student;
import com.test.respository.CourseRepository;
import com.test.respository.RegistrationRepository;
import com.test.respository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

 // POST method for registering student for a course
    public Registration registerStudentForCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        
        boolean isAlreadyRegistered = registrationRepository.findByStudentAndCourse(student, course).isPresent();
        if (isAlreadyRegistered) {
            throw new RuntimeException("Student is already registered for this course.");
        }

        
        Registration registration = new Registration();
        registration.setStudent(student);
        registration.setCourse(course);

        
        return registrationRepository.save(registration);
    }

 // GET method for fetching course names for a student
    public List<String> getCoursesForStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Fetching the courses the student is registered for
        List<Registration> registrations = registrationRepository.findByStudent(student);
        
        // Mapping registrations to their respective course names
        return registrations.stream()
                .map(registration -> registration.getCourse().getCourseName())  // Extract course name
                .collect(Collectors.toList());
    }
}
