package com.auth.auth_server.service;

import com.auth.auth_server.dto.RecaptchaResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecaptchaService {
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private final WebClient webClient = WebClient.create();

    public void validateToken(String token) {
        String url = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s",
                recaptchaSecret, token);

        RecaptchaResponse response = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(RecaptchaResponse.class)
                .block();

        if (response == null || !response.isSuccess() || response.getScore() < 0.5) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Verificación de reCAPTCHA fallida"
            );
        }
    }


}
