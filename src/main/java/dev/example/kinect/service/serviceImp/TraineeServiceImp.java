package dev.example.kinect.service.serviceImp;

import dev.example.kinect.model.Trainee;
import dev.example.kinect.model.enums.Role;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.TraineeService;
import dev.example.kinect.utils.PasswordHashingUtil;
import org.springframework.stereotype.Service;

@Service
public class TraineeServiceImp implements TraineeService {
    private final PasswordHashingUtil passwordHashingUtil;
    private final TraineeRepository traineeRepository;
    public TraineeServiceImp(TraineeRepository traineeRepository, PasswordHashingUtil passwordHashingUtil){
        this.traineeRepository = traineeRepository;
        this.passwordHashingUtil = passwordHashingUtil;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        trainee.setPassword(passwordHashingUtil.hashPassword(trainee.getPassword()));
        if (trainee.getEmail().equals("akram@gmail.com")) {
            trainee.setRole(Role.ADMIN);
        } else {
            trainee.setRole(Role.USER);
        }
        return traineeRepository.save(trainee);
    }
}
