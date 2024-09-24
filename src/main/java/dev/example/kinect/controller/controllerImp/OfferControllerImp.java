package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.OfferController;
import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.SearchCriteria;
import dev.example.kinect.service.OfferService;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferControllerImp implements OfferController {
    private final ProfileWorkflow profileWorkflow;
    private final OfferService offerService;
    public OfferControllerImp(ProfileWorkflow profileWorkflow, OfferService offerService){
        this.profileWorkflow = profileWorkflow;
        this.offerService = offerService;
    }

    @Override
    public ResponseEntity<OfferDTO> createOffer(OfferDTO offerDTO, Long profileId) throws ProfileNotFoundException, PlanningNotFoundException {
        return profileWorkflow.createOffer(offerDTO, profileId);
    }

    @Override
    public ResponseEntity<Void> deleteOffer(Long offerId) throws OfferNotFoundException {
        return profileWorkflow.deleteOffer(offerId);
    }

    @Override
    public OfferDTO updateOffer( Long offerId,
                                 OfferDTO offerDTO,
                                 Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException {
        return profileWorkflow.updateOffer(offerId, offerDTO, profileId);
    }

    @Override
    public ResponseEntity<List<OfferDTO>> listOffers() {
        try{
            List<OfferDTO> offerList = offerService.listOffers();
            return ResponseEntity.ok(offerList);
        } catch (Exception e) {
            System.out.println("error getting offers");
            return null;
        }
    }

    @Override
    public ResponseEntity<List<OfferDTO>> searchOffers(SearchCriteria criteria) {
        try{
            List<OfferDTO> offerDTOList = offerService.searchOffers(criteria);
            return ResponseEntity.ok(offerDTOList);
        } catch (Exception e) {
            System.out.println("error getting offers");
            return null;
        }
    }
}
