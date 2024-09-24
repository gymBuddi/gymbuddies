package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.TraineeDTO;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Role;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.RoleRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.TraineeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TraineeServiceImp implements TraineeService {
    private final TraineeRepository traineeRepository;
    private final RoleRepository roleRepository;
    String DEFAULT_PASSWORD = "password12";
    String DEFAULT_AUTHORITY = "ROLE_USER";
    public TraineeServiceImp(TraineeRepository traineeRepository, RoleRepository roleRepository) {
        this.traineeRepository = traineeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Trainee saveTrainee(TraineeDTO trainee) throws TraineeNotFoundException {
        Optional<Trainee> traineeOptional = traineeRepository.findByEmail(trainee.getEmail());
        if (traineeOptional.isPresent()) {
            throw new TraineeNotFoundException("user already exist");
        }
        Trainee savedTrainee = new Trainee();
        savedTrainee.setEmail(trainee.getEmail());
        savedTrainee.setActive(true);
        savedTrainee.setPassword(DEFAULT_PASSWORD);
        Optional<Role> role = roleRepository.findByAuthority(DEFAULT_AUTHORITY);
        role.ifPresent(value -> savedTrainee.setAuthorities(Set.of(value)));
        return traineeRepository.save(savedTrainee);
    }

    @Override
    public Trainee updateTrainee(TraineeDTO traineeDTO) {
        Optional<Trainee> traineeOptional = traineeRepository.findByEmail(traineeDTO.getEmail());
        if(traineeOptional.isPresent()) {
            Optional<Role> role = roleRepository.findByAuthority(DEFAULT_AUTHORITY);
            role.ifPresent(value -> traineeOptional.get().setAuthorities(Set.of(value)));
            return traineeRepository.save(traineeOptional.get());
        }
        return null;
    }

    @Override
    public List<Trainee> getActiveTrainees() {
        return traineeRepository.findAll().stream().filter(Trainee::isActive).collect(Collectors.toList());
    }

    @Override
    public List<Trainee> getInactiveTrainees() {
        return traineeRepository.findAll().stream().filter(trainee -> !trainee.isActive()).collect(Collectors.toList());
    }
}
