package com.auth.auth_server.dto;

public class TokenDto {
    private String accessToken;
    private String username;
    private String recaptchaToken;

    // Constructor privado para el Builder
    private TokenDto(Builder builder) {
        this.accessToken = builder.accessToken;
        this.username = builder.username;
        this.recaptchaToken = builder.recaptchaToken;
    }

    // Constructor vacío
    public TokenDto() {
    }

    // Constructor con parámetros
    public TokenDto(String accessToken, String username, String recaptchaToken) {
        this.accessToken = accessToken;
        this.username = username;
        this.recaptchaToken = recaptchaToken;
    }

    // Getters y Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public void setRecaptchaToken(String recaptchaToken) {
        this.recaptchaToken = recaptchaToken;
    }

    // Método estático para crear el Builder
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", recaptchaToken='" + recaptchaToken + '\'' +
                '}';
    }

    // Clase Builder estática
    public static class Builder {
        private String accessToken;
        private String username;
        private String recaptchaToken;

        private Builder() {
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder recaptchaToken(String recaptchaToken) {
            this.recaptchaToken = recaptchaToken;
            return this;
        }

        public TokenDto build() {
            return new TokenDto(this);
        }
    }

    // Override equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenDto tokenDto = (TokenDto) o;
        if (accessToken != null ? !accessToken.equals(tokenDto.accessToken) : tokenDto.accessToken != null) return false;
        if (username != null ? !username.equals(tokenDto.username) : tokenDto.username != null) return false;
        return recaptchaToken != null ? recaptchaToken.equals(tokenDto.recaptchaToken) : tokenDto.recaptchaToken == null;
    }

    @Override
    public int hashCode() {
        int result = accessToken != null ? accessToken.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (recaptchaToken != null ? recaptchaToken.hashCode() : 0);
        return result;
    }
}