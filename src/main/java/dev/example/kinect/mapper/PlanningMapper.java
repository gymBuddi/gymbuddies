package dev.example.kinect.mapper;

import dev.example.kinect.dto.PlanningDTO;
import dev.example.kinect.model.Planning;
import org.springframework.stereotype.Component;

@Component
public class PlanningMapper {
    public PlanningDTO toPlanningDTO(Planning planning){
        PlanningDTO planningDTO = new PlanningDTO();
        planningDTO.setDescription(planning.getDescription());
        planningDTO.setId(planning.getId());
        planningDTO.setGym(planning.getGym().getId());
        return planningDTO;
    }
}
