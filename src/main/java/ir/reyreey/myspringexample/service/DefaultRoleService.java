package ir.reyreey.myspringexample.service;

import ir.reyreey.myspringexample.repository.RoleRepository;
import ir.reyreey.myspringexample.repository.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 7/28/2024, Sunday
 **/
@Service
public class DefaultRoleService implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void insertRole(Role role) {
        roleRepository.save(role);
    }
}
