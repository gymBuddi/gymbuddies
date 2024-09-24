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
import dev.example.kinect.model.ProfilePreferences;
import dev.example.kinect.model.Request;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.model.enums.Status;
import dev.example.kinect.repository.GymRepository;
import dev.example.kinect.repository.MatchingRepository;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfilePreferencesRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.RequestRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.NotificationService;
import dev.example.kinect.service.OfferService;
import dev.example.kinect.service.PlanningService;
import dev.example.kinect.service.ProfileService;
import dev.example.kinect.service.RequestService;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    public Profile createProfile(ProfileDTO profileDTO) throws TraineeNotFoundException {
        Trainee trainee = traineeRepository.findById(profileDTO.getTrainee())
                .orElseThrow(() -> new TraineeNotFoundException("trainee not found"));
        return profileService.saveProfile(profileDTO, trainee);
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
    public ResponseEntity<Void> deletePlanning(Long planningId) throws PlanningNotFoundException {
        try {
            planningService.deletePlanning(planningId);
            return ResponseEntity.noContent().build();
        } catch (PlanningNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO, Long profileId) throws PlanningNotFoundException, ProfileNotFoundException {
        try {
            Planning planning = planningRepository.findById(offerDTO.getPlanning().getId())
                    .orElseThrow(() -> new PlanningNotFoundException("planning not found"));
            Profile profile = profileRepository.findById(profileId)
                    .orElseThrow(() -> new ProfileNotFoundException("user not found"));
            if (profile.getWorkoutPlannings().contains(planning)) {
                OfferDTO offerDTO1 = offerService.saveOffer(offerDTO, planning, profile);
                return ResponseEntity.ok(offerDTO1);
            }
            System.out.println("Planning not found in User plannings");
            return null;
        } catch (PlanningNotFoundException | ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteOffer(Long offerId) throws OfferNotFoundException {
        try {
            offerService.deleteOffer(offerId);
            return ResponseEntity.noContent().build();
        } catch (OfferNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @Override
    public OfferDTO updateOffer(Long offerId, OfferDTO offerDTO, Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException {
        Planning planning = planningRepository.findById(offerDTO.getPlanning().getId())
                .orElseThrow(() -> new PlanningNotFoundException("Planning not found"));
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
        return offerService.updateOffer(offerId, offerDTO, planning, profile);
    }

    @Override
    public Profile updateProfile(Long profileId) throws ProfileNotFoundException {
        /*Profile profile = profileRepository.findById(profileId).orElseThrow(() ->
                new ProfileNotFoundException("profile not found"));
        // create the preferences
        ProfilePreferences preferences = new ProfilePreferences();
        preferences.setProfile(profile);
        preferences.setExperienceLevel("BEGINNER");
        preferences.setFitnessGoals(Set.of("Gain weight", "Gain muscles"));
        preferences.setAvailability(Set.of("After 6pm"));
        preferences.setWorkoutPreferences(Set.of("Upper Body workouts", "NO cardio"));
        profile.setPreferences(preferences);
        profilePreferencesRepository.save(preferences);
        // set the preferences to the profile and save it in the database
        profileRepository.save(profile);*/
        return null;
    }

    @Override
    public RequestDTO createRequest(RequestDTO requestDTO, Long profileId) throws ProfileNotFoundException, OfferNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ProfileNotFoundException("user not found"));
        Offer offer = offerRepository.findById(requestDTO.getOffer())
                .orElseThrow(() -> new OfferNotFoundException("offer not found"));
        if (Objects.equals(offer.getProfile().getId(), profileId)) {
            System.out.println("You can't submit to you your offer");
            return null;
        }
        return requestService.saveRequest(requestDTO, profile, offer);

    }

    @Override
    @Transactional
    public String acceptRequest(Long requestId) throws RequestNotFoundException, ProfileNotFoundException {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException("request not found"));
        if (request.isEnabled() && request.getStatus().equals(Status.PENDING)) {
            request.setMatched(true);
            request.setLastUpdatedDate(LocalDateTime.now());
            request.setStatus(Status.ACCEPTED);
            requestRepository.save(request);
            // create a new match
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
    public String deniedRequest(Long requestId) throws RequestNotFoundException {
        Request request = requestRepository.findById(requestId)
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
