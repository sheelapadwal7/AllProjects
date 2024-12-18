package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.model.Registration;
import com.test.service.RegistrationService;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@GetMapping("/all")
	public String getBooks()
	{
		return "List of Books";
	}

	// POST method to register a student for a course
	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestParam Integer studentId, @RequestParam Integer courseId) {
		try {

			Registration registration = registrationService.registerStudentForCourse(studentId, courseId);
			return new ResponseEntity<>("Student with  stundetId " + studentId + " successfully registered for the "
					+ registration.getCourse().getCourseName() + "  course.", HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// GET method to get course names for a student
	@GetMapping("/courses/{studentId}")
	public ResponseEntity<Object> getCoursesForStudent(@PathVariable Integer studentId) {
		try {

			List<String> courseNames = registrationService.getCoursesForStudent(studentId);

			if (courseNames.isEmpty()) {

				return new ResponseEntity<>("Student is not registered for any courses.", HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(courseNames, HttpStatus.OK);
		} catch (RuntimeException e) {

			return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
