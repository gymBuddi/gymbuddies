package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.service.PlanningService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PlanningServiceImp implements PlanningService {
    private final ModelMapper modelMapper;
    private final PlanningRepository planningRepository;
    public PlanningServiceImp(ModelMapper modelMapper, PlanningRepository planningRepository){
        this.modelMapper = modelMapper;
        this.planningRepository = planningRepository;

    }
    @Override
    public PlanningDTO savePlanning(PlanningDTO planningDTO, Profile profile, Gym gym) {
        Planning planning = modelMapper.map(planningDTO, Planning.class);
        planning.setGym(gym);
        planning.setProfile(profile);
        planningRepository.save(planning);
        return modelMapper.map(planning, PlanningDTO.class);
    }
}
