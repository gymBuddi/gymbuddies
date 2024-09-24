package dev.example.kinect.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OfferDTO {
    private Long id;
    // private List<RequestDTO> request;
    private PlanningDTO planning;
    private LocalDateTime publicationDate;
    private LocalDateTime planningTime;
    private String gym;
    private Boolean isActive;
    private String workoutType;
    private String experienceLevel;
    private String offerCreater;
}
