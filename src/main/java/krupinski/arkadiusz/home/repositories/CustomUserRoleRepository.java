package krupinski.arkadiusz.home.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import krupinski.arkadiusz.home.models.CustomUserRole;
import krupinski.arkadiusz.home.models.RoleName;

@Repository
@Transactional
public interface CustomUserRoleRepository extends JpaRepository<CustomUserRole, Long> {
    Optional<CustomUserRole> findByRole(RoleName roleName);
}