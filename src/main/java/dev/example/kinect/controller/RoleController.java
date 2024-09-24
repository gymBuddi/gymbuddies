package dev.example.kinect.controller;

import dev.example.kinect.model.Role;
import dev.example.kinect.utils.AdminPaths;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AdminPaths.Role.PATH)
public interface RoleController {
    @PostMapping()
    void addRole(@RequestBody Role role);
}
