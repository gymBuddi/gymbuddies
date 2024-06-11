package dev.example.kinect.workflow.workflowImp;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.dto.RequestDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.GymRepository;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.OfferService;
import dev.example.kinect.service.PlanningService;
import dev.example.kinect.service.ProfileService;
import dev.example.kinect.service.RequestService;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileWorkflowImp implements ProfileWorkflow {
    private final ProfileService profileService;
    private final TraineeRepository traineeRepository;
    private final GymRepository gymRepository;
    private final PlanningService planningService;
    private final ProfileRepository profileRepository;
    private final PlanningRepository planningRepository;
    private final OfferService offerService;
    private final OfferRepository offerRepository;
    private final RequestService requestService;
    private final ModelMapper modelMapper;
    public ProfileWorkflowImp(ProfileService profileService, TraineeRepository traineeRepository,
                              ModelMapper modelMapper, GymRepository gymRepository, PlanningService planningService,
                              ProfileRepository profileRepository, PlanningRepository planningRepository,
                              OfferService offerService, OfferRepository offerRepository, RequestService requestService){
        this.profileService = profileService;
        this.traineeRepository = traineeRepository;
        this.modelMapper = modelMapper;
        this.gymRepository = gymRepository;
        this.planningService = planningService;
        this.profileRepository = profileRepository;
        this.planningRepository = planningRepository;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
        this.requestService = requestService;
    }

    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) throws TraineeNotFoundException {
        Trainee trainee = traineeRepository.findById(profileDTO.getTrainee())
                .orElseThrow(() -> new TraineeNotFoundException("trainee not found"));
        profileService.saveProfile(profileDTO, trainee);
        return profileDTO;
    }

    @Override
    public PlanningDTO createPlanning(PlanningDTO planningDTO, Long profile_id) throws ProfileNotFoundException, GymNotFoundException {
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("trainee not found"));
        Gym gym  = gymRepository.findById(planningDTO.getGym())
                .orElseThrow(() -> new GymNotFoundException("gym not found"));
        return planningService.savePlanning(planningDTO, profile, gym);
    }

    @Override
    public Void deletePlanning(Long planning_id) throws PlanningNotFoundException {
        return planningService.deletePlanning(planning_id);
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO, Long profile_id) throws PlanningNotFoundException, ProfileNotFoundException {
        Planning planning = planningRepository.findById(offerDTO.getPlanning())
                .orElseThrow(() -> new PlanningNotFoundException("planning not found"));
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("user not found"));
        return offerService.saveOffer(offerDTO, planning, profile);
    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO, Long profile_id) throws ProfileNotFoundException, OfferNotFoundException {
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("user not found"));
        Offer offer = offerRepository.findById(requestDTO.getOffer())
                .orElseThrow(() -> new OfferNotFoundException("offer not found"));
        return requestService.saveRequest(requestDTO, profile, offer);
    }

}
