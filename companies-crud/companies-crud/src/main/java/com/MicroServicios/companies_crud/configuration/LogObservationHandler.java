package com.MicroServicios.companies_crud.configuration;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(999)

public class LogObservationHandler implements ObservationHandler<Observation.Context> {

    private static final Logger logger = LoggerFactory.getLogger(LogObservationHandler.class);

    @Override
    public void onStart(Observation.Context context) {

        logger.info("LogObservationHandler::onStart {}",context.getName());
    }

    @Override
    public void onError(Observation.Context context) {

        logger.info("LogObservationHandler::onError {}",context.getName());
    }

    @Override
    public void onStop(Observation.Context context) {
        logger.info("LogObservationHandler::onStop {}",context.getName());
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
