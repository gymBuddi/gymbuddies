package dev.example.kinect.controller;

import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.LoginReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginReq loginReq) throws TraineeNotFoundException;

}
