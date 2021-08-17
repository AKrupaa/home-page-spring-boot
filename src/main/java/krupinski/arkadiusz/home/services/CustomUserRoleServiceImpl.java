package krupinski.arkadiusz.home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krupinski.arkadiusz.home.models.CustomUserRole;
import krupinski.arkadiusz.home.repositories.CustomUserRoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomUserRoleServiceImpl implements CustomUserRoleService {

    CustomUserRoleRepository customUserRoleRepository;

    @Autowired
    public CustomUserRoleServiceImpl(CustomUserRoleRepository customUserRoleRepository) {
        this.customUserRoleRepository = customUserRoleRepository;
    }

    @Override
    public List<CustomUserRole> listUserRoles() {
        return customUserRoleRepository.findAll();
    }

    @Override
    public void addUserRole(CustomUserRole userRole) {
        Optional<CustomUserRole> findByRole = customUserRoleRepository.findByRole(userRole.getRole());

        if (!findByRole.isPresent())
            customUserRoleRepository.save(userRole);
    }

    @Override
    public Optional<CustomUserRole> getUserRole(long id) {
        return customUserRoleRepository.findById(id);
    }

}