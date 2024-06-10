package dev.example.kinect.workflow.workflowImp;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.dto.TraineeDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.GymRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.PlanningService;
import dev.example.kinect.service.ProfileService;
import dev.example.kinect.service.serviceImp.ProfileServiceImp;
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
    private final ModelMapper modelMapper;
    public ProfileWorkflowImp(ProfileService profileService, TraineeRepository traineeRepository,
                              ModelMapper modelMapper, GymRepository gymRepository, PlanningService planningService,
                              ProfileRepository profileRepository){
        this.profileService = profileService;
        this.traineeRepository = traineeRepository;
        this.modelMapper = modelMapper;
        this.gymRepository = gymRepository;
        this.planningService = planningService;
        this.profileRepository = profileRepository;
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
        planningService.savePlanning(planningDTO, profile, gym);
        return null;
    }
}
