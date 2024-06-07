package dev.example.kinect.controller;

import dev.example.kinect.dto.GymDTO;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AdminPaths.Gym.PATH)
public interface GymController {
    @PostMapping()
    GymDTO saveGym(@RequestBody GymDTO gymDTO);
}
