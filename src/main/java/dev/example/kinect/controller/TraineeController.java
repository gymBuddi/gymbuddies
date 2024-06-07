package dev.example.kinect.controller;

import dev.example.kinect.dto.TraineeDTO;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AdminPaths.Trainee.PATH)
public interface TraineeController {
    @PostMapping()
    Trainee saveTrainee(@RequestBody Trainee trainee);
}
