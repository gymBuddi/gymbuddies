package dev.example.kinect.service;

import dev.example.kinect.model.AuthResponse;
import dev.example.kinect.model.LoginRequest;
import dev.example.kinect.model.RegisterRequest;

public interface AuthService {

    public AuthResponse login(LoginRequest login);
    public void register(RegisterRequest register);
}
