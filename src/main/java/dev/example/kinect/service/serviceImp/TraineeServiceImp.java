package dev.example.kinect.service.serviceImp;

import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.model.enums.Role;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.TraineeService;
import dev.example.kinect.utils.PasswordHashingUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraineeServiceImp implements TraineeService {
    private final PasswordHashingUtil passwordHashingUtil;
    private final TraineeRepository traineeRepository;
    public TraineeServiceImp(TraineeRepository traineeRepository, PasswordHashingUtil passwordHashingUtil){
        this.traineeRepository = traineeRepository;
        this.passwordHashingUtil = passwordHashingUtil;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) throws TraineeNotFoundException {
        Optional<Trainee> traineeOptional = Optional.ofNullable(traineeRepository.findByEmail(trainee.getEmail()));
        if (traineeOptional.isPresent()) {
            throw new TraineeNotFoundException("user already exist");
        }
        trainee.setPassword(passwordHashingUtil.hashPassword(trainee.getPassword()));
        if (trainee.getEmail().equals("akram@gmail.com")) {
            trainee.setRole(Role.ADMIN);
        } else {
            trainee.setRole(Role.USER);
        }
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

}
