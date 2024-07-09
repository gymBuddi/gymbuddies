package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.auth.JwtUtils;
import dev.example.kinect.controller.AuthController;
import dev.example.kinect.model.ErrorResponse;
import dev.example.kinect.model.LoginReq;
import dev.example.kinect.model.LoginRes;
import dev.example.kinect.model.Trainee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImp implements AuthController {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public AuthControllerImp(JwtUtils jwtUtils, AuthenticationManager authenticationManager){
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> login(LoginReq loginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();
            Trainee attemptedTrainee = new Trainee(email, "");
            String token = jwtUtils.createToken(attemptedTrainee);
            LoginRes response = new LoginRes(email, token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            ErrorResponse error = new ErrorResponse("Bad Credentials", HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }
}
