package dev.example.kinect.service;

import dev.example.kinect.dto.RequestDTO;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Profile;

public interface RequestService {
    RequestDTO saveRequest(RequestDTO requestDTO, Profile profile, Offer offer);
}
