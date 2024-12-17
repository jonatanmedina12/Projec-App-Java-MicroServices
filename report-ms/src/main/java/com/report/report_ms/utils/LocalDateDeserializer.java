package com.report.report_ms.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER_DDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATTER_YYYYMMDD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String dateString = parser.readValueAs(String.class);
        try {
            return LocalDate.parse(dateString, FORMATTER_DDMMYYYY);
        } catch (Exception e) {
            return LocalDate.parse(dateString, FORMATTER_YYYYMMDD);
        }
    }
}