package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.GymDTO;
import dev.example.kinect.model.Gym;
import dev.example.kinect.repository.GymRepository;
import dev.example.kinect.service.GymService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GymServiceImp implements GymService {
    private final GymRepository gymRepository;
    private final ModelMapper modelMapper;
    public GymServiceImp(GymRepository gymRepository, ModelMapper modelMapper){
        this.gymRepository = gymRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GymDTO saveGym(GymDTO gymDTO) {
        Gym gym = modelMapper.map(gymDTO, Gym.class);
        Gym savedGym = gymRepository.save(gym);
        return modelMapper.map(savedGym, GymDTO.class);
    }
}
