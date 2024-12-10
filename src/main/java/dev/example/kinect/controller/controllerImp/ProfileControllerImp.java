package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.ProfileController;
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
import dev.example.kinect.service.ProfileService;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileControllerImp implements ProfileController {
    private final ProfileService traineeService;
    private final ProfileWorkflow profileWorkflow;
    public ProfileControllerImp(ProfileService traineeService, ProfileWorkflow profileWorkflow){
        this.traineeService = traineeService;
        this.profileWorkflow = profileWorkflow;
    }

    @Override
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("server ok \uD83D\uDC4D");
    }

    @Override
    public List<ProfileDTO> loadAllProfiles() {
        return traineeService.loadAllProfiles();
    }

    @Override
    public ProfileDTO loadProfile(String username) throws ProfileNotFoundException {
        return traineeService.loadProfile(username);
    }

    @Override
    public Profile saveProfile(ProfileDTO profileDTO, String authHeader) throws TraineeNotFoundException {
        return profileWorkflow.createProfile(profileDTO, authHeader);
    }

    @Override
    public Profile updateProfile(Long profileId) throws ProfileNotFoundException {
        return profileWorkflow.updateProfile(profileId);
    }

    @Override
    public PlanningDTO createPlanning(PlanningDTO planningDTO, Long profile_id) throws GymNotFoundException, ProfileNotFoundException {
        return profileWorkflow.createPlanning(planningDTO, profile_id);
    }

    @Override
    public ResponseEntity<Void> deletePlanning(Long planningId) throws PlanningNotFoundException {
        return profileWorkflow.deletePlanning(planningId);
    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO, Long profile_id)
            throws ProfileNotFoundException, OfferNotFoundException {
        return profileWorkflow.createRequest(requestDTO, profile_id);
    }

    @Override
    public String acceptRequest(Long request_id) throws RequestNotFoundException, ProfileNotFoundException {
        return profileWorkflow.acceptRequest(request_id);
    }

    @Override
    public String deniedRequest(Long requestId) throws RequestNotFoundException {
        return profileWorkflow.deniedRequest(requestId);
    }

    @Override
    public String cancelRequest(Long request_id) throws RequestNotFoundException {
        return profileWorkflow.cancelRequest(request_id);
    }

}
