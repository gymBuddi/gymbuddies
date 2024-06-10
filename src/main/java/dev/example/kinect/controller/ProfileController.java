package dev.example.kinect.controller;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.dto.ProfileDTO;
import dev.example.kinect.exception.GymNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.exception.TraineeNotFoundException;
import dev.example.kinect.utils.AdminPaths;
import dev.example.kinect.utils.PathParam;
import dev.example.kinect.utils.RestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    ProfileDTO saveProfile(@RequestBody ProfileDTO traineeDTO) throws TraineeNotFoundException;

    @PostMapping(AdminPaths.Profile.PLANNING + PathParam.ID)
    PlanningDTO createPlanning(@RequestBody PlanningDTO planningDTO,
                               @PathVariable(value = RestParam.ID) Long profile_id)
            throws GymNotFoundException, ProfileNotFoundException;
}
