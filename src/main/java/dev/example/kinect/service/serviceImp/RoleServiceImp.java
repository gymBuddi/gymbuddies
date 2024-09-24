package dev.example.kinect.service.serviceImp;

import dev.example.kinect.model.Role;
import dev.example.kinect.repository.RoleRepository;
import dev.example.kinect.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImp(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }
}
