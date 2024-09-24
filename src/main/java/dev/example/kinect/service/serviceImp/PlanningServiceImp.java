package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.service.PlanningService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanningServiceImp implements PlanningService {
    private final ModelMapper modelMapper;
    private final PlanningRepository planningRepository;
    private final ProfileRepository profileRepository;
    public PlanningServiceImp(ModelMapper modelMapper, PlanningRepository planningRepository,
                              ProfileRepository profileRepository){
        this.modelMapper = modelMapper;
        this.planningRepository = planningRepository;
        this.profileRepository = profileRepository;

    }
    @Override
    public PlanningDTO savePlanning(PlanningDTO planningDTO, Profile profile, Gym gym) {
        Planning planning = new Planning();
        planning.setDescription(planningDTO.getDescription());
        planning.setGym(gym);
        planning.setProfile(profile);
        profile.getWorkoutPlannings().add(planning);
        gym.getWorkoutplannings().add(planning);
        planningRepository.save(planning);
        return planningDTO;
    }

    @Override
    public void deletePlanning(Long planningId) throws PlanningNotFoundException {
        Planning planning = planningRepository.findById(planningId)
                .orElseThrow(() -> new PlanningNotFoundException("planning not found"));
        planningRepository.delete(planning);
    }
}
