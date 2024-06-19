package dev.example.kinect.controller;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RequestMapping(AdminPaths.Offer.PATH)
public interface OfferController {
    @PostMapping(PathParam.PROFILE_ID)
    OfferDTO createOffer(@RequestBody OfferDTO offerDTO, @PathVariable(value = RestParam.PROFILE_ID) Long profile_id) throws ProfileNotFoundException, PlanningNotFoundException;
    @DeleteMapping(PathParam.OFFER_ID)
    Void deleteOffer(@PathVariable(value = RestParam.OFFER_ID) Long offer_id) throws OfferNotFoundException;
    @PostMapping("/{offerId}")
    OfferDTO updateOffer(@PathVariable("offerId") Long offerId,
                         @RequestBody OfferDTO offerDTO,
                         @RequestParam("profileId") Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException;
}


//