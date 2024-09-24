package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.GymController;
import dev.example.kinect.dto.GymDTO;
import dev.example.kinect.model.Gym;
import dev.example.kinect.service.GymService;
import dev.example.kinect.service.serviceImp.GymServiceImp;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GymControllerImp implements GymController {
    private final GymService gymService;
    public GymControllerImp(GymService gymService){
        this.gymService = gymService;
    }
    @Override
    public Gym saveGym(GymDTO gymDTO) {
        return gymService.saveGym(gymDTO);
    }
}
