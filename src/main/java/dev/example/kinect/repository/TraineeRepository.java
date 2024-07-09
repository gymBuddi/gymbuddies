package dev.example.kinect.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import dev.example.kinect.model.Trainee;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long>, JpaSpecificationExecutor<Trainee> {
    Optional<Trainee> findByEmail(String email);
}
