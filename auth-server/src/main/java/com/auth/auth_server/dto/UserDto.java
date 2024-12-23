package com.auth.auth_server.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank(message = "El username es requerido")
    private String username;

    @NotBlank(message = "La contraseña es requerida")
    private String password;



    // Constructor vacío
    public UserDto() {
    }

    // Constructor con parámetros
    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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

    // Builder estático
    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    // Clase Builder interna
    public static class UserDtoBuilder {
        private String username;
        private String password;

        UserDtoBuilder() {
        }

        public UserDtoBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDto build() {
            return new UserDto(username, password);
        }
    }

    // toString método
    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (username != null ? !username.equals(userDto.username) : userDto.username != null) return false;
        return password != null ? password.equals(userDto.password) : userDto.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}