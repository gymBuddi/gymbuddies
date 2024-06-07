package dev.example.kinect.repository;

import dev.example.kinect.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>,
        JpaSpecificationExecutor<Profile> {

    Optional<Profile> findByUsername(String username);
}
