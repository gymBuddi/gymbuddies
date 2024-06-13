package dev.example.kinect.workflow;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.dto.RequestDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.RequestNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;

public interface ProfileWorkflow {
    ProfileDTO createProfile(ProfileDTO profileDTO) throws TraineeNotFoundException;
    PlanningDTO createPlanning(PlanningDTO planningDTO, Long profile_id) throws ProfileNotFoundException, GymNotFoundException;
    Void deletePlanning(Long planning_id, Long profile_id) throws PlanningNotFoundException;
    OfferDTO createOffer(OfferDTO offerDTO, Long profile_id) throws PlanningNotFoundException, ProfileNotFoundException;
    Void deleteOffer(Long offer_id) throws OfferNotFoundException;
    RequestDTO createRequest(RequestDTO requestDTO, Long profile_id) throws ProfileNotFoundException, OfferNotFoundException;
    String acceptRequest(Long request_id) throws RequestNotFoundException, ProfileNotFoundException;
    String deniedRequest(Long request_id) throws RequestNotFoundException;
}
