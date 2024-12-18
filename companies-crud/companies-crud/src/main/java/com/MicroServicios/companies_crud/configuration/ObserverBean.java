package com.MicroServicios.companies_crud.configuration;
import io.micrometer.tracing.propagation.Propagator;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.*;
import io.micrometer.tracing.handler.DefaultTracingObservationHandler;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.LogRecordProcessor;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.semconv.ResourceAttributes;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;

@Configuration(proxyBeanMethods = false)
public class ObserverBean {

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    public DefaultTracingObservationHandler defaultTracingObservationHandler(Tracer tracer) {
        return new DefaultTracingObservationHandler(tracer);
    }



    @Bean
    public SdkLoggerProvider sdkLoggerProvider(Environment environment, ObjectProvider<LogRecordProcessor> processors) {
        var applicationName = environment.getProperty("spring.application.name", "application");
        var activeProfile = environment.getActiveProfiles().length > 0 ?
                environment.getActiveProfiles()[0] : "default";

        var resourceAttributes = Resource.create(Attributes.of(
                ResourceAttributes.SERVICE_NAME, applicationName,
                ResourceAttributes.SERVICE_VERSION, "1.0.0",
                ResourceAttributes.DEPLOYMENT_ENVIRONMENT, activeProfile,
                ResourceAttributes.HOST_NAME, System.getProperty("user.name"),
                ResourceAttributes.OS_TYPE, System.getProperty("os.name")
        ));

        var builder = SdkLoggerProvider.builder()
                .setResource(Resource.getDefault().merge(resourceAttributes));

        processors.orderedStream().forEach(builder::addLogRecordProcessor);

        return builder.build();
    }

    @Bean
    public OpenTelemetry openTelemetry(SdkLoggerProvider loggerProvider,
                                       SdkTracerProvider tracerProvider,
                                       ContextPropagators contextPropagators) {
        return OpenTelemetrySdk.builder()
                .setLoggerProvider(loggerProvider)
                .setTracerProvider(tracerProvider)
                .setPropagators(contextPropagators)
                .build();
    }

    @Bean
    public LogRecordProcessor logRecordProcessor() {
        var otlpLogRecord = OtlpGrpcLogRecordExporter.builder()
                .setEndpoint("http://localhost:4317")
                .setTimeout(Duration.ofSeconds(5))
                .build();

        return BatchLogRecordProcessor.builder(otlpLogRecord)
                .setScheduleDelay(Duration.ofSeconds(1))
                .setMaxQueueSize(2048)
                .setMaxExportBatchSize(512)
                .build();
    }

    @Bean
    public SpanAspect spanAspect(MethodInvocationProcessor processor) {
        return new SpanAspect(processor);
    }

    @Bean
    public MethodInvocationProcessor methodInvocationProcessor(
            NewSpanParser newSpanParser,
            Tracer tracer,
            BeanFactory beanFactory) {
        return new ImperativeMethodInvocationProcessor(
                newSpanParser,
                tracer,
                beanFactory::getBean,
                beanFactory::getBean
        );
    }

    @Bean
    public NewSpanParser spanParser() {
        return new DefaultNewSpanParser();
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}