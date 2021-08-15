package krupinski.arkadiusz.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import krupinski.arkadiusz.home.models.CustomUser;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    CustomUser findByLogin(String login);
}