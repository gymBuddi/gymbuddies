package dev.example.kinect.repository;

import dev.example.kinect.model.Trainee;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long>, JpaSpecificationExecutor<Trainee> {
    Trainee findByEmail(String email);
}
