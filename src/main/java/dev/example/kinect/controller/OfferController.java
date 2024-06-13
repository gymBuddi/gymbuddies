package dev.example.kinect.controller;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;

@RequestMapping(AdminPaths.Offer.PATH)
public interface OfferController {
    @PostMapping(PathParam.PROFILE_ID)
    OfferDTO createOffer(@RequestBody OfferDTO offerDTO, @PathVariable(value = RestParam.PROFILE_ID) Long profile_id) throws ProfileNotFoundException, PlanningNotFoundException;
    @DeleteMapping(PathParam.OFFER_ID)
    Void deleteOffer(@PathVariable(value = RestParam.OFFER_ID) Long offer_id) throws OfferNotFoundException;
}
