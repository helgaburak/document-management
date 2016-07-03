package pl.com.bottega.documentmanagement.controller;

/**
 * Created by Piotr on 03.07.2016.
 */
public class SignupRequest {
    private String login;
    private String password;
    private Long employeeId;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}

