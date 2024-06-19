package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.OfferController;
import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.workflow.ProfileWorkflow;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferControllerImp implements OfferController {
    private final ProfileWorkflow profileWorkflow;
    public OfferControllerImp(ProfileWorkflow profileWorkflow){
        this.profileWorkflow = profileWorkflow;
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO, Long profile_id) throws ProfileNotFoundException, PlanningNotFoundException {
        return profileWorkflow.createOffer(offerDTO, profile_id);
    }

    @Override
    public Void deleteOffer(Long offer_id) throws OfferNotFoundException {
        return profileWorkflow.deleteOffer(offer_id);
    }

    @Override
    public OfferDTO updateOffer( Long offerId,
                                 OfferDTO offerDTO,
                                 Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException {
        return profileWorkflow.updateOffer(offerId, offerDTO, profileId);
    }
}
