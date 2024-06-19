package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.RequestDTO;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.Profile;
import dev.example.kinect.model.Request;
import dev.example.kinect.model.enums.Status;
import dev.example.kinect.repository.OfferRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.repository.RequestRepository;
import dev.example.kinect.service.RequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RequestServiceImp implements RequestService {
    private final RequestRepository requestRepository;
    private final ProfileRepository profileRepository;
    private final OfferRepository offerRepository;
    public RequestServiceImp(RequestRepository requestRepository,ProfileRepository profileRepository,
                             OfferRepository offerRepository){
        this.requestRepository = requestRepository;
        this.profileRepository = profileRepository;
        this.offerRepository = offerRepository;
    }
    @Override
    public RequestDTO saveRequest(RequestDTO requestDTO, Profile profile, Offer offer) {
        Request request = new Request();
        request.setCreationDate(LocalDateTime.now());
        request.setSender(profile);
        request.setOffer(offer);
        request.setMatched(false);
        request.setStatus(Status.PENDING);
        request.setLastUpdatedDate(LocalDateTime.now());
        profile.getRequests().add(request);
        offer.getRequest().add(request);
        profileRepository.save(profile);
        offerRepository.save(offer);
        //requestRepository.save(request);
        return requestDTO;
    }
}
