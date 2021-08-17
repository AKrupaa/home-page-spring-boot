package krupinski.arkadiusz.home.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import krupinski.arkadiusz.home.models.CustomUser;
import krupinski.arkadiusz.home.models.RoleName;
import krupinski.arkadiusz.home.repositories.CustomUserRepository;
import krupinski.arkadiusz.home.repositories.CustomUserRoleRepository;

@Service
@Transactional
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserRepository customUserRepository;
    private final CustomUserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserServiceImpl(CustomUserRepository customUserRepository,
            CustomUserRoleRepository userRoleRepository) {
        this.customUserRepository = customUserRepository;
        this.userRoleRepository = userRoleRepository;
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public void addCustomUser(CustomUser customUser) {
        customUser.setEnabled(true);
        // customUser.getUserRoles()
                // .add(userRoleRepository.findByRole(RoleName.ROLE_USER).orElseThrow(IllegalStateException::new));

        customUser.setPassword(hashPassword(customUser.getPassword()));
        this.customUserRepository.save(customUser);

    }

    @Override
    public void deleteCustomUser(long id) {
        this.customUserRepository.deleteById(id);
    }

    @Override
    public CustomUser findByLogin(String login) {
        return this.customUserRepository.findByLogin(login);
    }

    @Override
    public void editCustomUser(CustomUser customUser) {
        this.customUserRepository.save(customUser);

    }

    @Override
    public List<CustomUser> getUsers() {
        return this.customUserRepository.findAll();
    }

    @Override
    public Optional<CustomUser> getUser(long id) {
        return this.customUserRepository.findById(id);
    }
}
