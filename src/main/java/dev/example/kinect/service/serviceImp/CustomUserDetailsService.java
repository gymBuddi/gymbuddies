package dev.example.kinect.service.serviceImp;

import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.TraineeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TraineeRepository traineeRepository;
    public CustomUserDetailsService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return traineeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Trainee not found"));
    }
}
