package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    
    public ProfileServiceImp(ProfileRepository profileRepository, ModelMapper modelMapper){
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProfileDTO> loadAllProfiles() {
        return profileRepository.findAll().stream().map(profile -> modelMapper.map(profile, ProfileDTO.class)).toList();
    }

    @Override
    public ProfileDTO loadProfile(String username) throws ProfileNotFoundException {
        Profile profile = profileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException("trainee was not found in db"));
        return modelMapper.map(profile, ProfileDTO.class);
    }

    @Override
    public void saveProfile(ProfileDTO profileDTO, Trainee trainee) {
        profileDTO.setCreationDate(LocalDate.now());
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        profile.setTrainee(trainee);
        trainee.setProfile(profile);
        profileRepository.save(profile);
    }
}
