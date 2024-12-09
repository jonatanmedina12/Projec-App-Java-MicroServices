package com.MicroServicios.companies_crud.services;

import java.time.LocalDateTime;

public interface ApiError {
    LocalDateTime getTimestamp();
    void setTimestamp(LocalDateTime timestamp);

    int getStatus();
    void setStatus(int status);

    String getMessage();
    void setMessage(String message);

    String getPath();
    void setPath(String path);
}
