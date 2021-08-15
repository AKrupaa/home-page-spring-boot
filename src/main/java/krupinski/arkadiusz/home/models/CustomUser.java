package krupinski.arkadiusz.home.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;

    // @NotEmpty(message = "{error.field.required}")
    // @Min(value = 5, message = "error.min.five")
    private String lastName;
    // @Min(value = 5, message = "error.min.five")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    // @NotEmpty(message = "{error.field.required}")
    // @Min(value = 5, message = "error.min.five")
    private String login;
    @JsonIgnore
    // @NotEmpty(message = "{error.field.required}")
    // @Min(value = 5, message = "error.min.five")
    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CustomUserRole> userRoles = new HashSet<>(0);

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<CustomUserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<CustomUserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
