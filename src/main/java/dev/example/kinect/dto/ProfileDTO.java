package dev.example.kinect.dto;

import dev.example.kinect.model.ProfilePreferences;
import dev.example.kinect.model.enums.ActivityLevel;
import dev.example.kinect.model.enums.FitnessLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarPicture;
    private LocalDate birthDate;
    private boolean verified;
    private Long age;
    private double height;
    private double weight;
    private List<PlanningDTO> workoutPlannings;
    private List<RequestDTO> requests;
    private List<OfferDTO> offers;
    private ProfilePreferencesDTO preferences;
}
