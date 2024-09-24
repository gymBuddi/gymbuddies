package dev.example.kinect.controller;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.dto.RequestDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.OfferNotFoundException;
import dev.example.kinect.exception.PlanningNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.RequestNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.model.Profile;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = AdminPaths.Profile.PATH)
public interface ProfileController {
    @GetMapping("/healthz")
    ResponseEntity<String> ok();
    @GetMapping()
    List<ProfileDTO> loadAllProfiles();
    @GetMapping("/{username}")
    ProfileDTO loadProfile(@PathVariable(value = "username") String username) throws ProfileNotFoundException;
    @PostMapping()
    Profile saveProfile(@RequestBody ProfileDTO profileDTO) throws TraineeNotFoundException;
    @PutMapping(PathParam.PROFILE_ID)
    Profile updateProfile(@PathVariable(value = RestParam.PROFILE_ID) Long profileId) throws ProfileNotFoundException;
    @PostMapping(AdminPaths.Profile.PLANNING + PathParam.PROFILE_ID)
    PlanningDTO createPlanning(@RequestBody PlanningDTO planningDTO,
                               @PathVariable(value = RestParam.PROFILE_ID) Long profileId)
            throws GymNotFoundException, ProfileNotFoundException;
    @DeleteMapping(AdminPaths.Profile.PLANNING + PathParam.PLANNING_ID)
    ResponseEntity<Void> deletePlanning(@PathVariable(value = RestParam.PLANNING_ID) Long planningId) throws PlanningNotFoundException;
    @PostMapping(AdminPaths.Request.PATH + PathParam.PROFILE_ID)
    RequestDTO createRequest(@RequestBody RequestDTO requestDTO, @PathVariable(value = RestParam.PROFILE_ID) Long profileId)
            throws ProfileNotFoundException, OfferNotFoundException;
    @GetMapping(AdminPaths.Request.PATH + AdminPaths.Request.ACCEPT + PathParam.REQUEST_ID)
    String acceptRequest(@PathVariable(value = RestParam.REQUEST_ID) Long requestId) throws RequestNotFoundException, ProfileNotFoundException;
    @GetMapping(AdminPaths.Request.PATH + AdminPaths.Request.DENIED + PathParam.REQUEST_ID)
    String deniedRequest(@PathVariable(value = RestParam.REQUEST_ID) Long requestId) throws RequestNotFoundException;
    @GetMapping(AdminPaths.Request.PATH + AdminPaths.Request.CANCEL + PathParam.REQUEST_ID)
    String cancelRequest(@PathVariable(value = RestParam.REQUEST_ID) Long request_id) throws RequestNotFoundException;
}
