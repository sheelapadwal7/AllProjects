package com.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        
        if ("user".equals(username) && "password".equals(password)) {
            String token = jwtUtils.generateJwtToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("password",password);
            response.put("token", token);
            return response;
        }
        throw new RuntimeException("Invalid credentials");
    }
    
   
}

