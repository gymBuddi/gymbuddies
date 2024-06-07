package dev.example.kinect.repository;

import dev.example.kinect.model.Gym;
import dev.example.kinect.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GymRepository extends JpaRepository<Gym, Long>, JpaSpecificationExecutor<Profile> {
}
