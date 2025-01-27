package dev.example.kinect.service;

import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Trainee;

import java.util.List;

public interface ProfileService {

    List<ProfileDTO> loadAllProfiles();
    ProfileDTO loadProfile(String username) throws ProfileNotFoundException;
    Profile saveProfile(ProfileDTO profileDTO, Trainee trainee);
    String getProfileEmail(Long profile_id) throws ProfileNotFoundException;
}
