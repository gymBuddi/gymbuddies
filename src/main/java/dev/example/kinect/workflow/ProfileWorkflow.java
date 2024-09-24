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
import dev.example.kinect.model.Profile;
import org.springframework.http.ResponseEntity;

public interface ProfileWorkflow {
    Profile createProfile(ProfileDTO profileDTO) throws TraineeNotFoundException;
    PlanningDTO createPlanning(PlanningDTO planningDTO, Long profile_id) throws ProfileNotFoundException, GymNotFoundException;
    ResponseEntity<Void> deletePlanning(Long planningId) throws PlanningNotFoundException;
    ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO, Long profileId) throws PlanningNotFoundException, ProfileNotFoundException;
    ResponseEntity<Void> deleteOffer(Long offerId) throws OfferNotFoundException;
    RequestDTO createRequest(RequestDTO requestDTO, Long profileId) throws ProfileNotFoundException, OfferNotFoundException;
    String acceptRequest(Long request_id) throws RequestNotFoundException, ProfileNotFoundException;
    String deniedRequest(Long requestId) throws RequestNotFoundException;
    public String cancelRequest(Long request_id) throws RequestNotFoundException;
    OfferDTO updateOffer(Long offerId, OfferDTO offerDTO, Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException;
    Profile updateProfile(Long profileId) throws ProfileNotFoundException;
}
