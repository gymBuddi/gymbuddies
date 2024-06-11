package dev.example.kinect.workflow;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;

public interface ProfileWorkflow {
    ProfileDTO createProfile(ProfileDTO profileDTO) throws TraineeNotFoundException;

    PlanningDTO createPlanning(PlanningDTO planningDTO, Long profile_id) throws ProfileNotFoundException, GymNotFoundException;

    Void deletePlanning(Long planning_id) throws PlanningNotFoundException;
}
