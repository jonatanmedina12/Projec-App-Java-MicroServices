package com.auth.auth_server.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.recaptcha")
public class RecaptchaConfig {
    private String secret;
    private float scoreThreshold;
    private String verifyUrl;

    // Getters y Setters
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public float getScoreThreshold() {
        return scoreThreshold;
    }

    public void setScoreThreshold(float scoreThreshold) {
        this.scoreThreshold = scoreThreshold;
    }

    public String getVerifyUrl() {
        return verifyUrl;
    }

    public void setVerifyUrl(String verifyUrl) {
        this.verifyUrl = verifyUrl;
    }


}

