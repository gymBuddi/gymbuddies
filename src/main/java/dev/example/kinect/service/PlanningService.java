package dev.example.kinect.service;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;

public interface PlanningService {
    PlanningDTO savePlanning(PlanningDTO planningDTO, Profile profile, Gym gym);
    Void deletePlanning(Long planning_id, Long profile_id) throws PlanningNotFoundException;
}
