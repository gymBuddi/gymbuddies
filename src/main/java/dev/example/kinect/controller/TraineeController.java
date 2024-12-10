package dev.example.kinect.controller;

import dev.example.kinect.dto.TraineeDTO;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(AdminPaths.Trainee.PATH)
public interface TraineeController {
    @PostMapping()
    Trainee saveTrainee(@RequestBody TraineeDTO trainee) throws TraineeNotFoundException;
    @PutMapping()
    Trainee updateTrainee(@RequestBody TraineeDTO trainee) throws TraineeNotFoundException;
    @GetMapping(AdminPaths.Trainee.ACTIVE)
    List<Trainee> getActiveTrainees();
    @GetMapping(AdminPaths.Trainee.INACTIVE)
    List<Trainee> getInactiveTrainees();
}
