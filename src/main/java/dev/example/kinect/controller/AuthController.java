package dev.example.kinect.controller;

import dev.example.kinect.model.AuthResponse;
import dev.example.kinect.model.LoginRequest;
import dev.example.kinect.model.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request);

}
