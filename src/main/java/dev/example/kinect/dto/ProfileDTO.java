package dev.example.kinect.dto;

import dev.example.kinect.model.enums.ActivityLevel;
import dev.example.kinect.model.enums.FitnessLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ProfileDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarPicture;
    private LocalDate birthDate;
    private LocalDate creationDate;
    private boolean verified;
    private Long trainee;
    private Long age;
    private double height;
    private double weight;
    @Enumerated(value = EnumType.ORDINAL)
    private FitnessLevel fitnessLevel;
    @Enumerated(value = EnumType.ORDINAL)
    private ActivityLevel activityLevel;
    private List<PlanningDTO> workoutPlannings;
    private List<RequestDTO> requests;
    private List<OfferDTO> offers;
}
