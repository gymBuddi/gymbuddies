package dev.example.kinect.service;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;

public interface OfferService {
    OfferDTO saveOffer(OfferDTO offerDTO, Planning planning, Profile profile);
}
