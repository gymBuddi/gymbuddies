package dev.example.kinect.service.serviceImp;

import dev.example.kinect.helper.JwtUtils;
import dev.example.kinect.model.AuthResponse;
import dev.example.kinect.model.LoginRequest;
import dev.example.kinect.model.RegisterRequest;
import dev.example.kinect.model.Role;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.model.enums.RoleEnum;
import dev.example.kinect.repository.RoleRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {
    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final TraineeRepository traineeRepository;
    private final RoleRepository roleRepository;
    String DEFAULT_AUTHORITY = "ROLE_USER";
    public AuthServiceImp(AuthenticationManager authManager, UserDetailsService userDetailsService,
                          JwtUtils jwtUtils, PasswordEncoder passwordEncoder, TraineeRepository traineeRepository,
                          RoleRepository roleRepository) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.traineeRepository = traineeRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public AuthResponse login(LoginRequest login) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getUsername(),
                            login.getPassword()
                    )
            );

            // Generate token
            String token = jwtUtils.generateToken(authentication.getName());
            return new AuthResponse(token);
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            throw new RuntimeException("Authentication failed", e);
        }
    }

    @Override
    public void register(RegisterRequest register) {
        Trainee savedTrainee = new Trainee();
        savedTrainee.setUsername(register.getUsername());
        savedTrainee.setEnabled(true);
        savedTrainee.setAccountNonLocked(true); // Ensure this is set
        savedTrainee.setPassword(passwordEncoder.encode(register.getPassword()));

        Optional<Role> role = roleRepository.findByRole(RoleEnum.ADMIN);
        if (role.isPresent()) {
            savedTrainee.setRoles(Set.of(role.get()));
            System.out.println("Role assigned: " + role.get().getRole());
            System.out.println("Authorities: " + role.get().getAuthorities());
        } else {
            System.out.println("No role found for ADMIN");
        }

        traineeRepository.save(savedTrainee);
    }
}
