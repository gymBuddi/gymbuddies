package dev.example.kinect.listeners;

import dev.example.kinect.model.Authority;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuthorityListener {
    @PrePersist
    public void onPersist(Authority authority){
        authority.setCreationDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(Authority authority){
        authority.setLastModifiedDate(LocalDateTime.now());
    }
}
