package dev.example.kinect.service.serviceImp;

import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.TraineeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraineeServiceImp implements TraineeService, UserDetailsService {
    private final TraineeRepository traineeRepository;
    public TraineeServiceImp(TraineeRepository traineeRepository){
        this.traineeRepository = traineeRepository;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) throws TraineeNotFoundException {
        Optional<Trainee> traineeOptional = traineeRepository.findByEmail(trainee.getEmail());
        if (traineeOptional.isPresent()) {
            throw new TraineeNotFoundException("user already exist");
        }
       /* if (trainee.getEmail().equals("akram@gmail.com")) {
            // trainee.setRole(Role.ADMIN);
        } else {
            // trainee.setRole(Role.USER);
        }*/
        trainee.setActive(true);
        return traineeRepository.save(trainee);
    }
    @Override
    public List<Trainee> getActiveTrainees() {
        return traineeRepository.findAll().stream().filter(Trainee::isActive).collect(Collectors.toList());
    }

    @Override
    public List<Trainee> getInactiveTrainees() {
        return traineeRepository.findAll().stream().filter(trainee -> !trainee.isActive()).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return traineeRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(
                "user not found in db"));
    }
}
