package pl.com.bottega.documentmanagement.controller;

/**
 * Created by Piotr on 03.07.2016.
 */
public class LoginRequest {

    private String login;
    private String password;
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {
        return password;
    }




}
