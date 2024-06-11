package dev.example.kinect.controller;

import dev.example.kinect.dto.OfferDTO;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AdminPaths.Offer.PATH)
public interface OfferController {
    @PostMapping(PathParam.ID)
    OfferDTO createOffer(@RequestBody OfferDTO offerDTO, @PathVariable(value = RestParam.ID) Long profile_id) throws ProfileNotFoundException, PlanningNotFoundException;
}
