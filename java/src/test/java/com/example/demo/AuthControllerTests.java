package com.example.demo;

import com.example.demo.controller.AuthController;
import com.example.demo.security.JwtResponse;
import com.example.demo.security.LoginRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class AuthControllerTests {

    @Autowired
    AuthController authController;

    @Test
    public void authenticateUserTest(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("a");

        ResponseEntity<?> response = authController.authenticateUser(loginRequest);
        Assert.assertNotNull(response);

        JwtResponse jwt = (JwtResponse) response.getBody();

        System.out.println(jwt);
    }
}
