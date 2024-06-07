package dev.example.kinect.model;

import dev.example.kinect.model.enums.FitnessLevel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatarPicture;
    private LocalDate birthDate;
    private LocalDate creationDate;
    private Long age;
    private boolean verified;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;
    @Enumerated(value = EnumType.ORDINAL)
    private FitnessLevel fitnessLevel;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Planning> workoutPlannings;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Request> requests;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Offer> offers;
}
