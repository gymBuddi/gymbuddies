package dev.example.kinect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePreferencesDTO {
    private Long id;
    private Set<String> workoutPreferences;
    private String experienceLevel;
    private Set<String> availability;
    private Set<String> fitnessGoals;
}
