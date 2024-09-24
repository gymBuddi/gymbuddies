package dev.example.kinect.listeners;

import dev.example.kinect.model.Trainee;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class TraineeListener {
    @PrePersist
    public void onPersist(Trainee trainee){
        trainee.setCreationDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(Trainee trainee){
        trainee.setLastModifiedDate(LocalDateTime.now());
    }
}
