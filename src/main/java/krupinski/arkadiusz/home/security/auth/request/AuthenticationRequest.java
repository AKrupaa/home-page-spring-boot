package krupinski.arkadiusz.home.security.auth.request;

public class AuthenticationRequest {

    private String login;
    private String password;

    // need default constructor for JSON Parsing
    public AuthenticationRequest() {

    }

    public AuthenticationRequest(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
