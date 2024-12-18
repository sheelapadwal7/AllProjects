package com.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test; // Correct import for JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.test.service.UserService;
import com.test.model.User;

@WebMvcTest(UserController.class) 
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;  

    @MockBean
    private UserService userService;  

    @Test
    void getUserById_shouldReturnUser_whenUserExists() throws Exception {
        // Arrange
        Long userId = 1L;
        User mockUser = new User(userId, "John Doe", "john@example.com");

      
        when(userService.getUserById(userId)).thenReturn(mockUser);

     
        mockMvc.perform(get("/users/{id}", userId)  
                .contentType(MediaType.APPLICATION_JSON))  
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    void getUserById_shouldReturnNotFound_whenUserDoesNotExist() throws Exception {
      
        Long userId = 2L;
        when(userService.getUserById(userId)).thenReturn(null); 

       
        mockMvc.perform(get("/users/{id}", userId)  
                .contentType(MediaType.APPLICATION_JSON))  
                .andExpect(status().isNotFound());  
    }
}
