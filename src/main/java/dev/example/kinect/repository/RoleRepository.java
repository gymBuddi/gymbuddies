package dev.example.kinect.repository;

import dev.example.kinect.model.Role;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from roles r where r.authority in (?)", nativeQuery = true)
    Optional<Role> findByAuthority(String authority);
}
