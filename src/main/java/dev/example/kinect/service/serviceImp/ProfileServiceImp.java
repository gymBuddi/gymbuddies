package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.dto.ProfilePreferencesDTO;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.ProfilePreferences;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.repository.ProfilePreferencesRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.TraineeRepository;
import dev.example.kinect.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProfileServiceImp implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final TraineeRepository traineeRepository;
    private final ProfilePreferencesRepository profilePreferencesRepository;
    
    public ProfileServiceImp(ProfileRepository profileRepository, ModelMapper modelMapper,
                             TraineeRepository traineeRepository,
                             ProfilePreferencesRepository profilePreferencesRepository){
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.traineeRepository = traineeRepository;
        this.profilePreferencesRepository = profilePreferencesRepository;
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
    @Transactional
    public Profile saveProfile(ProfileDTO profileDTO, Trainee trainee) {
        // map profileDTO to profile
        Profile profile = new Profile();
        profile.setTrainee(trainee);
        profile.setUsername(profileDTO.getUsername());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setVerified(profileDTO.isVerified());
        // set the profile to trainee object
        trainee.setProfile(profile);
        // add profile preferences
        //ProfilePreferences preferences = getProfilePreferences(profileDTO, profile);
        // save the profile
        Profile savedProfile = profileRepository.save(profile);
        traineeRepository.save(trainee);
        //profilePreferencesRepository.save(preferences);
        return savedProfile;
    }

    private static ProfilePreferences getProfilePreferences(ProfileDTO profileDTO, Profile profile) {
        ProfilePreferencesDTO profilePreferencesDTO = profileDTO.getPreferences();
        ProfilePreferences preferences = new ProfilePreferences();
        preferences.setWorkoutPreferences(profilePreferencesDTO.getWorkoutPreferences());
        preferences.setFitnessGoals(profilePreferencesDTO.getFitnessGoals());
        preferences.setAvailability(profilePreferencesDTO.getAvailability());
        preferences.setExperienceLevel(profilePreferencesDTO.getExperienceLevel());
        preferences.setProfile(profile);
        return preferences;
    }

    @Override
    public String getProfileEmail(Long profile_id) throws ProfileNotFoundException {
        Profile profile = profileRepository.findById(profile_id)
                .orElseThrow(() -> new ProfileNotFoundException("profile not found"));
        Trainee trainee = profile.getTrainee();
        return trainee.getUsername();
    }
}
