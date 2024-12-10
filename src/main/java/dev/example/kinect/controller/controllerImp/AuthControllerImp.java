package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.AuthController;
import dev.example.kinect.model.AuthResponse;
import dev.example.kinect.model.LoginRequest;
import dev.example.kinect.model.RegisterRequest;
import dev.example.kinect.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllerImp implements AuthController {

    private final AuthService authService;
    public AuthControllerImp(AuthService authService) {
        this.authService = authService;
    }
    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @Override
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
}
