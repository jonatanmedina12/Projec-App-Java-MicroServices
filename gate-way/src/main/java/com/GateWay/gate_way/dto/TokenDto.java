package com.GateWay.gate_way.dto;

public class TokenDto {
    private String accessToken;

    // Constructor privado para el Builder
    private TokenDto(Builder builder) {
        this.accessToken = builder.accessToken;
    }

    // Constructor vacío
    public TokenDto() {
    }

    // Constructor con parámetros
    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getters y Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // Método estático para crear el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder estática
    public static class Builder {
        private String accessToken;

        private Builder() {
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public TokenDto build() {
            return new TokenDto(this);
        }
    }

    // Optional: Override equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenDto tokenDto = (TokenDto) o;
        return accessToken != null ? accessToken.equals(tokenDto.accessToken) : tokenDto.accessToken == null;
    }

    @Override
    public int hashCode() {
        return accessToken != null ? accessToken.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}