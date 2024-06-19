package dev.example.kinect.service;

import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Trainee;

import java.util.List;

public interface TraineeService {
    Trainee saveTrainee(Trainee trainee) throws TraineeNotFoundException;
    List<Trainee> getActiveTrainees();
    List<Trainee> getInactiveTrainees();
}
