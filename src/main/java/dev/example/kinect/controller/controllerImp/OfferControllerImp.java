package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.OfferController;
import dev.example.kinect.dto.OfferDTO;
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
}
