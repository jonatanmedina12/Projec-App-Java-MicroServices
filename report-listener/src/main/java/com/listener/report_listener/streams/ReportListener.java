package com.listener.report_listener.streams;


import com.listener.report_listener.documents.ReportDocuments;
import com.listener.report_listener.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ReportListener {

    private static final Logger logger = LoggerFactory.getLogger(ReportListener.class);

    @Autowired
    private ReportRepository reportRepository;

    @Bean
    public Consumer<String> consumerReport() {
        return report -> {
            try {
                logger.info("Recibiendo reporte para procesar: {}", report);

                ReportDocuments document = ReportDocuments.builder()
                        .content(report)
                        .build();

                ReportDocuments savedDocument = reportRepository.save(document);
                logger.info("Reporte guardado exitosamente con ID: {}", savedDocument.getId());
            } catch (Exception e) {
                logger.error("Error al procesar y guardar el reporte: {}", e.getMessage(), e);
                throw e; // Relanzamos la excepción para que el sistema de mensajería sepa que hubo un error
            }
        };
    }




}
