package krupinski.arkadiusz.home.services;

import java.util.List;
import java.util.Optional;

import krupinski.arkadiusz.home.models.CustomUser;

public interface CustomUserService {

    public void addCustomUser(CustomUser customUser);

    public void deleteCustomUser(long id);

    public CustomUser findByLogin(String login);

    public void editCustomUser(CustomUser customUser);

    public List<CustomUser> getUsers();

    public Optional<CustomUser> getUser(long id);
}
