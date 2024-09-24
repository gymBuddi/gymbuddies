package dev.example.kinect.controller;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Offer;
import dev.example.kinect.model.SearchCriteria;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(AdminPaths.Offer.PATH)
public interface OfferController {
    @PostMapping(PathParam.PROFILE_ID)
    ResponseEntity<OfferDTO> createOffer(@RequestBody OfferDTO offerDTO, @PathVariable(value = RestParam.PROFILE_ID) Long profileId) throws ProfileNotFoundException, PlanningNotFoundException;
    @DeleteMapping(PathParam.OFFER_ID)
    ResponseEntity<Void> deleteOffer(@PathVariable(value = RestParam.OFFER_ID) Long offerId) throws OfferNotFoundException;
    @PutMapping("/{offerId}")
    OfferDTO updateOffer(@PathVariable("offerId") Long offerId,
                         @RequestBody OfferDTO offerDTO,
                         @RequestParam("profileId") Long profileId)
            throws OfferNotFoundException, PlanningNotFoundException, ProfileNotFoundException;

    @GetMapping()
    ResponseEntity<List<OfferDTO>> listOffers();

    @PostMapping("/search")
    ResponseEntity<List<OfferDTO>> searchOffers(@RequestBody(required = false) SearchCriteria criteria);
}


//
