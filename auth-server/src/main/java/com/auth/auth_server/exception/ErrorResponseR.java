package com.auth.auth_server.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponseR {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private Map<String, String> details;

    // Constructor privado para el Builder
    private ErrorResponseR(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.error = builder.error;
        this.details = builder.details;
    }

    // Constructor vacío
    public ErrorResponseR() {
    }

    // Constructor completo
    public ErrorResponseR(LocalDateTime timestamp, int status, String error, Map<String, String> details) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.details = details;
    }

    // Getters y Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    // Método estático para crear el Builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder
    public static class Builder {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private Map<String, String> details;

        private Builder() {
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder details(Map<String, String> details) {
            this.details = details;
            return this;
        }

        public ErrorResponseR build() {
            return new ErrorResponseR(this);
        }
    }

    // Override de equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorResponseR that = (ErrorResponseR) o;

        if (status != that.status) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        return details != null ? details.equals(that.details) : that.details == null;
    }

    @Override
    public int hashCode() {
        int result = timestamp != null ? timestamp.hashCode() : 0;
        result = 31 * result + status;
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ErrorResponseR{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", details=" + details +
                '}';
    }
}