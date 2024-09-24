package dev.example.kinect.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_preferences")
public class ProfilePreferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    @CollectionTable(name = "workout_preferences", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "workout_type")
    private Set<String> workoutPreferences;

    @Column(name = "experience_level")
    private String experienceLevel;

    @ElementCollection
    @CollectionTable(name = "availability", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "available_time")
    private Set<String> availability;

    @ElementCollection
    @CollectionTable(name = "fitness_goals", joinColumns = @JoinColumn(name = "preferences_id"))
    @Column(name = "goal")
    private Set<String> fitnessGoals;
    @ToString.Exclude
    @OneToOne(mappedBy = "preferences")
    private Profile profile;
}
