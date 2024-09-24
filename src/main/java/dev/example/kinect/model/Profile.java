package dev.example.kinect.model;

import dev.example.kinect.listeners.ProfileListener;
import dev.example.kinect.model.enums.ActivityLevel;
import dev.example.kinect.model.enums.FitnessLevel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(ProfileListener.class)
public class Profile extends AbstractEntity{
    private String username;
    private String firstName;
    private String lastName;
    private String avatarPicture;
    private LocalDate birthDate;
    private Long age;
    private double height;
    private double weight;
    @Enumerated(value = EnumType.STRING)
    private ActivityLevel activityLevel;
    private boolean verified;
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;
    @Enumerated(value = EnumType.STRING)
    private FitnessLevel fitnessLevel;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Planning> workoutPlannings;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Request> requests;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Offer> offers;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Notification> notifications;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "preferences_id", referencedColumnName = "id")
    private ProfilePreferences preferences;
}
