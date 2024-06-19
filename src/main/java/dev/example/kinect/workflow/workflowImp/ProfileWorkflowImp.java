package dev.example.kinect.workflow.workflowImp;

import dev.example.kinect.dto.NotificationDTO;
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
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Match;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Request;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.model.enums.Status;
import dev.example.kinect.repository.GymRepository;
import dev.example.kinect.repository.MatchingRepository;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.RequestRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.NotificationService;
import dev.example.kinect.service.OfferService;
import dev.example.kinect.service.PlanningService;
import dev.example.kinect.service.ProfileService;
import dev.example.kinect.service.RequestService;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    private final RequestRepository requestRepository;
    private final MatchingRepository matchRepository;
    private final NotificationService notificationService;
    public ProfileWorkflowImp(ProfileService profileService, TraineeRepository traineeRepository,
                              GymRepository gymRepository, PlanningService planningService,
                              ProfileRepository profileRepository, PlanningRepository planningRepository,
                              OfferService offerService, OfferRepository offerRepository, RequestService requestService,
                              RequestRepository requestRepository, MatchingRepository matchRepository,
                              NotificationService notificationService){
        this.profileService = profileService;
        this.traineeRepository = traineeRepository;
        this.gymRepository = gymRepository;
        this.planningService = planningService;
        this.profileRepository = profileRepository;
        this.planningRepository = planningRepository;
        this.offerService = offerService;
        this.offerRepository = offerRepository;
        this.requestService = requestService;
        this.requestRepository = requestRepository;
        this.matchRepository = matchRepository;
        this.notificationService = notificationService;
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
    public Void deletePlanning(Long planning_id, Long profile_id) throws PlanningNotFoundException {
        return planningService.deletePlanning(planning_id, profile_id);
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO, Long profile_id) throws PlanningNotFoundException, ProfileNotFoundException {
        Planning planning = planningRepository.findById(offerDTO.getPlanning())
                .orElseThrow(() -> new PlanningNotFoundException("planning not found"));
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("user not found"));
        if (profile.getWorkoutPlannings().contains(planning)){
            return offerService.saveOffer(offerDTO, planning, profile);
        }
        throw new PlanningNotFoundException("planning not found in your profile");
    }

    @Override
    public Void deleteOffer(Long offer_id) throws OfferNotFoundException {
        return offerService.deletedOffer(offer_id);
    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO, Long profile_id) throws ProfileNotFoundException, OfferNotFoundException {
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("user not found"));
        Offer offer = offerRepository.findById(requestDTO.getOffer())
                .orElseThrow(() -> new OfferNotFoundException("offer not found"));
        if (profile.getOffers().contains(offer)){
            return requestService.saveRequest(requestDTO, profile, offer);
        }
        throw new OfferNotFoundException("offer not found in your profile");
    }

    @Override
    @Transactional
    public String acceptRequest(Long request_id) throws RequestNotFoundException, ProfileNotFoundException {
        Request request = requestRepository.findById(request_id)
                .orElseThrow(() -> new RequestNotFoundException("request not found"));
        if (request.isEnabled() && request.getStatus().equals(Status.PENDING)) {
            request.setMatched(true);
            request.setLastUpdatedDate(LocalDateTime.now());
            request.setStatus(Status.ACCEPTED);
            requestRepository.save(request);
            Match match = new Match();
            match.setRequest(request);
            match.setOffer(request.getOffer());
            match.setMatchDate(LocalDateTime.now());
            matchRepository.save(match);
            // notify the user who made the request
            Profile requestUser = request.getSender();
            String message = "Your request for the offer : {}, has been accepted!" + request.getOffer().getId();
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setProfile(requestUser.getId());
            notificationDTO.setMessage(message);
            notificationService.createNotification(notificationDTO);
            //notificationService.notifyUser(requestUser.getId(), "Your Offer has been accepted", message);
            return "congrats! new match is saved";
        }
        return "request is no longer enabled";
    }

    @Override
    public String deniedRequest(Long request_id) throws RequestNotFoundException {
        Request request = requestRepository.findById(request_id)
                .orElseThrow(() -> new RequestNotFoundException("request not found"));
        if (request.isEnabled() && request.getStatus().equals(Status.PENDING)) {
            request.setMatched(true);
            request.setLastUpdatedDate(LocalDateTime.now());
            request.setStatus(Status.DENIED);
            requestRepository.save(request);
            return "Request has been denied";
        }
        return "request is no longer enabled";
    }

    @Override
    public String cancelRequest(Long request_id) throws RequestNotFoundException {
        Request request = requestRepository.findById(request_id)
                .orElseThrow(() -> new RequestNotFoundException("request not found"));
        request.setLastUpdatedDate(LocalDateTime.now());
        request.setStatus(Status.CANCELED);
        request.setEnabled(false);
        requestRepository.save(request);
        return null;
    }

}
