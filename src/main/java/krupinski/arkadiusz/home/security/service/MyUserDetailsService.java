package krupinski.arkadiusz.home.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import krupinski.arkadiusz.home.models.CustomUser;
import krupinski.arkadiusz.home.models.CustomUserRole;
import krupinski.arkadiusz.home.services.CustomUserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final CustomUserService customUserService;

    @Autowired
    public MyUserDetailsService(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        CustomUser customUser = customUserService.findByLogin(login);
        List<GrantedAuthority> authorities = buildUserAuthority(customUser.getUserRoles());
        return buildUserForAuthentication(customUser, authorities);

    }

    // Converts CustomUser user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(CustomUser customUser, List<GrantedAuthority> authorities) {

        return new User(customUser.getLogin(), customUser.getPassword(), customUser.isEnabled(), true, true, true,
                authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<CustomUserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        // Build user's authorities
        for (CustomUserRole userRole : userRoles) {
            switch (userRole.getRole()) {
                case ROLE_USER:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                    break;
                case ROLE_ADMIN:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    break;
                default:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                    break;
            }

        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }
}
