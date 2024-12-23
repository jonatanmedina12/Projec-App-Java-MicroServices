package com.auth.auth_server.dto;

public class LoginRequestDto {

    private String username;
    private String password;
    private String recaptchaToken;

    // Constructor vacío
    public LoginRequestDto() {
    }

    // Constructor con parámetros
    public LoginRequestDto(String username, String password, String recaptchaToken) {
        this.username = username;
        this.password = password;
        this.recaptchaToken = recaptchaToken;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                ", recaptchaToken='" + recaptchaToken + '\'' +
                '}';
    }
}
