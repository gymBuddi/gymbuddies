package dev.example.kinect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    private String gym;
    private LocalDate startDate;
    private LocalDate endDate;
    private String workoutType;
    private String experienceLevel;
}
