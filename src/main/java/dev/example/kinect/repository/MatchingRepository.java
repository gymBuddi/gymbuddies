package dev.example.kinect.repository;

import dev.example.kinect.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MatchingRepository extends JpaRepository<Match, Long>, JpaSpecificationExecutor<Match> {
}
