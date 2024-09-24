package dev.example.kinect.repository;

import dev.example.kinect.model.ProfilePreferences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePreferencesRepository extends JpaRepository<ProfilePreferences, Long> {
}
