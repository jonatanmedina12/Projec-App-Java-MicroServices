package com.listener.report_listener.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportDocuments {

    @Id
    private String id;
    private String content;

    // Constructor privado para forzar el uso del Builder
    private ReportDocuments() {}

    // Constructor con todos los campos para el Builder
    private ReportDocuments(String id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Implementación del Builder
    public static class Builder {
        private String id;
        private String content;

        public Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public ReportDocuments build() {
            return new ReportDocuments(id, content);
        }
    }

    // Método estático para obtener una nueva instancia del Builder
    public static Builder builder() {
        return new Builder();
    }

    // toString() para debugging y logging
    @Override
    public String toString() {
        return "ReportDocuments{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}