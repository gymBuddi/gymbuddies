package dev.example.kinect.listeners;

import dev.example.kinect.model.Profile;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class ProfileListener {
    @PrePersist
    public void onPersist(Profile profile){
        profile.setCreationDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(Profile profile){
        profile.setLastModifiedDate(LocalDateTime.now());
    }
}
