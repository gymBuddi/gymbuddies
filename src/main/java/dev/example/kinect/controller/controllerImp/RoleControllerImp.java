package dev.example.kinect.controller.controllerImp;

import dev.example.kinect.controller.RoleController;
import dev.example.kinect.model.Role;
import dev.example.kinect.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleControllerImp implements RoleController {
    private final RoleService roleService;
    public RoleControllerImp(RoleService roleService){
        this.roleService = roleService;
    }
    @Override
    public void addRole(Role role) {
        roleService.addRole(role);
    }
}
