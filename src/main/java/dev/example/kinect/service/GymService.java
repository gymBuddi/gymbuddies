package dev.example.kinect.service;

import dev.example.kinect.dto.GymDTO;
import dev.example.kinect.model.Gym;

public interface GymService {
    Gym saveGym(GymDTO gymDTO);
}
