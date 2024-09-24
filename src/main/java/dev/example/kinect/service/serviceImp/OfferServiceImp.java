package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.mapper.PlanningMapper;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Planning;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Request;
import dev.example.kinect.model.SearchCriteria;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.PlanningRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImp implements OfferService {
    private final OfferRepository offerRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private final PlanningMapper planningMapper;
    private final PlanningRepository planningRepository;
    public OfferServiceImp(OfferRepository offerRepository, ModelMapper modelMapper, ProfileRepository profileRepository,
                           PlanningRepository planningRepository, PlanningMapper planningMapper){
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.profileRepository = profileRepository;
        this.planningRepository = planningRepository;
        this.planningMapper = planningMapper;
    }
    @Override
    @Transactional
    public OfferDTO saveOffer(OfferDTO offerDTO, Planning planning, Profile profile) {
        // create the offer
        Offer offer = new Offer();
        offer.setPublicationDate(LocalDateTime.now());
        offer.setIsActive(offerDTO.getIsActive());
        offer.setPlanningTime(LocalDateTime.of(2024,9,18,17,30));
        offer.setProfile(profile);
        offer.setPlanning(planning);

        profile.getOffers().add(offer);
        planning.getOffers().add(offer);
        // save the offer
        offerRepository.save(offer);
        return offerDTO;
    }


    @Override
    public OfferDTO updateOffer(Long offerId, OfferDTO offerDTO, Planning planning, Profile profile)
            throws OfferNotFoundException {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        // Update offer properties
        offer.setPublicationDate(offerDTO.getPublicationDate());
        offer.setPlanningTime(offerDTO.getPlanningTime());
        offer.setIsActive(offerDTO.getIsActive());

        // Update relationships
        offer.setProfile(profile);
        offer.setPlanning(planning);
        profile.getOffers().add(offer);
        planning.getOffers().add(offer);

        // Save updated entities
        profileRepository.save(profile);
        planningRepository.save(planning);
        offerRepository.save(offer);

        return offerDTO;
    }

    @Override
    public List<OfferDTO> listOffers() {
        /*return offerRepository.findAll().stream().map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());*/
        List<OfferDTO> offerDTOList = new ArrayList<>();
        List<Offer> offerList = offerRepository.findAll();
        offerList.forEach(offer -> {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setId(offer.getId());
            offerDTO.setGym(offer.getPlanning().getGym().getName());
            offerDTO.setOfferCreater(offer.getProfile().getUsername());
            offerDTO.setPlanning(planningMapper.toPlanningDTO(offer.getPlanning()));
            offerDTO.setIsActive(offer.getIsActive());
            offerDTO.setPublicationDate(offer.getPublicationDate());
            offerDTO.setPlanningTime(offer.getPlanningTime());
            offerDTOList.add(offerDTO);
        });
        return offerDTOList;
    }

    @Override
    public List<OfferDTO> searchOffers(SearchCriteria criteria) {
        if (criteria == null) {
            // return listOffers();

        }
        /*return listOffers().stream()
                .filter(offer -> matchesGym(offer, criteria.getGym()))
                .filter(offer -> matchesDateRange(offer, criteria.getStartDate(), criteria.getEndDate()))
                .filter(offer -> matchesWorkoutType(offer, criteria.getWorkoutType()))
                .filter(offer -> matchesExperienceLevel(offer, criteria.getExperienceLevel()))
                .filter(offer -> matchesMaxDistance(offer, criteria.getUserLocation(), criteria.getMaxDistance()))
                .collect(Collectors.toList());*/
        return null;
    }

    @Override
    @Transactional
    public void deleteOffer(Long offerId) throws OfferNotFoundException {
        Offer offer = offerRepository.findById(offerId)
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
    }
}
