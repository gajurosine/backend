package com.rca.rosinenzambaza.template.v1.serviceImpls;


import com.rca.rosinenzambaza.template.v1.exceptions.NotFoundException;
import com.rca.rosinenzambaza.template.v1.models.Role;
import com.rca.rosinenzambaza.template.v1.repositories.RoleRepository;
import com.rca.rosinenzambaza.template.v1.services.RoleService;
import com.rca.rosinenzambaza.template.v1.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        try {
            return roleRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Role getRoleById(UUID id) {
        try {
            return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with id " + id + " was not found"));
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Role createRole(String roleName) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCode(roleName);
        try {
            Optional<Role> role1= roleRepository.findRoleByRoleName(roleName);
            if (role1.isEmpty()){
                return roleRepository.save(role);
            }
            else{
                return null;
            }
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Role updateRole(UUID id, String roleName) {
        try {
            Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with id " + id + " was not found"));
            role.setRoleName(roleName);
            role.setCode(roleName);
            return roleRepository.save(role);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public void deleteRole(UUID id) {
        try {
            Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with id " + id + " was not found"));
            roleRepository.deleteById(id);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
        }
    }
}
