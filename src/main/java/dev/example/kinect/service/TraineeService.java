package dev.example.kinect.service;

import dev.example.kinect.dto.TraineeDTO;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Trainee;

import java.util.List;

public interface TraineeService {
    Trainee saveTrainee(TraineeDTO trainee) throws TraineeNotFoundException;
    Trainee updateTrainee(TraineeDTO traineeDTO);
    List<Trainee> getActiveTrainees();
    List<Trainee> getInactiveTrainees();
}
