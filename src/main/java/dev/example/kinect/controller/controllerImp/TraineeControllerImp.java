package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.TraineeController;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.service.TraineeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TraineeControllerImp implements TraineeController {
    private final TraineeService traineeService;
    public TraineeControllerImp(TraineeService traineeService){
        this.traineeService = traineeService;
    }

    @Override
    public Trainee saveTrainee(Trainee trainee) {
        return traineeService.saveTrainee(trainee);
    }
}
