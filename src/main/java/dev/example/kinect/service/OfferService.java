package dev.example.kinect.service;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.SearchCriteria;

import java.util.List;

public interface OfferService {
    OfferDTO saveOffer(OfferDTO offerDTO, Planning planning, Profile profile);
    void deleteOffer(Long offerId) throws OfferNotFoundException;
    OfferDTO updateOffer(Long offerId, OfferDTO offerDTO, Planning planning, Profile profile)
            throws OfferNotFoundException;
    List<OfferDTO> listOffers();

    List<OfferDTO> searchOffers(SearchCriteria criteria);
}
