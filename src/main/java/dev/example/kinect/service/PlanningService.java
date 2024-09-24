package dev.example.kinect.service;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import org.springframework.http.ResponseEntity;

public interface PlanningService {
    PlanningDTO savePlanning(PlanningDTO planningDTO, Profile profile, Gym gym);
    void deletePlanning(Long planningId) throws PlanningNotFoundException;
}
