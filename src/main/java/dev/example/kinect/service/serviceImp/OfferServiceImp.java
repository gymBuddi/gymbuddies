package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImp implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    public OfferServiceImp(OfferRepository offerRepository, ModelMapper modelMapper){
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public OfferDTO saveOffer(OfferDTO offerDTO, Planning planning, Profile profile) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offer.setProfile(profile);
        offer.setPlanning(planning);
        profile.getOffers().add(offer);
        planning.getOffers().add(offer);
        offerRepository.save(offer);
        return offerDTO;
    }
}
