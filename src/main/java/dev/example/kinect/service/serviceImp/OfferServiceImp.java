package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Request;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfferServiceImp implements OfferService {
    private final OfferRepository offerRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final PlanningRepository planningRepository;
    public OfferServiceImp(OfferRepository offerRepository, ModelMapper modelMapper, ProfileRepository profileRepository,
                           PlanningRepository planningRepository){
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.profileRepository = profileRepository;
        this.planningRepository = planningRepository;
    }
    @Override
    @Transactional
    public OfferDTO saveOffer(OfferDTO offerDTO, Planning planning, Profile profile) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        offer.setProfile(profile);
        offer.setPlanning(planning);
        profile.getOffers().add(offer);
        planning.getOffers().add(offer);
        profileRepository.save(profile);
        planningRepository.save(planning);
        //offerRepository.save(offer);
        return offerDTO;
    }

    @Override
    public Void deletedOffer(Long offer_id) throws OfferNotFoundException {
        Offer offer = offerRepository.findById(offer_id)
                .orElseThrow(() -> new OfferNotFoundException("offer not found"));
        offer.setProfile(null); // breaking the relation between the offer and the user
        offer.getPlanning().getOffers().remove(offer);
        offer.setPlanning(null); // breaking the relation between the offer and the planning
        offer.getProfile().getOffers().remove(offer);
        // break the relation between the offer and its requests
        for (Request request : offer.getRequest()){
            request.setEnabled(true);
            request.setOffer(null);
        }
        offerRepository.delete(offer);
        return null;
    }
}
