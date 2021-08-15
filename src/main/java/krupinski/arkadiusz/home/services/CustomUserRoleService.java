package krupinski.arkadiusz.home.services;

import krupinski.arkadiusz.home.models.CustomUserRole;

import java.util.List;
import java.util.Optional;

public interface CustomUserRoleService {
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addUserRole(CustomUserRole userRole);

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<CustomUserRole> listUserRoles();

    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    Optional<CustomUserRole> getUserRole(long id);
}
