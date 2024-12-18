# Microservicios Spring Cloud & Spring Boot 🚀

Este proyecto implementa una arquitectura de microservicios moderna utilizando Spring Boot y Spring Cloud, diseñada para gestionar operaciones CRUD de empresas con capacidades de reportería y observabilidad.

## 🏗️ Arquitectura

El sistema está compuesto por los siguientes componentes:

### 🌟 Servicios Core

- **Discovery Server**: Eureka Server para registro y descubrimiento de servicios
- **Config Server**: Gestión centralizada de configuración con Git
- **API Gateway**: Punto de entrada único para todas las peticiones
- **Companies CRUD**: Servicio principal para operaciones CRUD de empresas
- **Report MS**: Microservicio de generación de reportes
- **Report Listener**: Consumidor de eventos para procesamiento de reportes

### 🔐 Autenticación y Respaldo

- **Auth Server**: Servicio de autenticación
- **Companies Fallback**: Servicio de respaldo para el servicio de empresas

### 📊 Observabilidad

Sistema completo de observabilidad implementado con:
- Grafana
- OpenTelemetry
- Docker

### 🔄 Mensajería

- **Kafka MSA**: Sistema de mensajería para comunicación asíncrona entre servicios

## 💻 Tecnologías Utilizadas

- **Spring Boot**: Framework base para los microservicios
- **Spring Cloud**: Herramientas para sistemas distribuidos
- **Docker**: Containerización de servicios
- **Kafka**: Sistema de mensajería
- **MongoDB**: Base de datos NoSQL
- **PostgreSQL**: Base de datos relacional
- **OpenTelemetry**: Instrumentación y trazabilidad
- **Grafana**: Visualización de métricas y logs

## 🔧 Configuración del Entorno

1. **Prerequisitos**
   - Java 21+
   - Docker & Docker Compose
   - Maven
   - Git

2. **Servicios de Infraestructura**
   ```bash
   docker-compose up -d
   ```

3. **Orden de Inicio de Servicios**
   1. Discovery Server
   2. Config Server
   3. API Gateway
   4. Servicios de Negocio

## 🚦 Endpoints Principales

- **Discovery Server**: `http://localhost:8761`
- **API Gateway**: `http://localhost:8080`
- **Config Server**: `http://localhost:8888`
- **Companies CRUD**: `http://localhost:[puerto_dinámico]`
- **Report Service**: `http://localhost:[puerto_dinámico]`

## 📈 Monitoreo y Observabilidad

- **Grafana**: `http://localhost:3000`
- **OpenTelemetry Collector**: `http://localhost:4317`



## 💡 Características Principales

- Arquitectura basada en microservicios
- Configuración centralizada
- Service discovery
- Circuit breaker pattern
- Distributed tracing
- Logging centralizado
- Métricas en tiempo real
- Mensajería asíncrona
- Seguridad centralizada

## 🤝 Contribución

1. Fork el repositorio
2. Cree una rama para su característica (`git checkout -b feature/AmazingFeature`)
3. Commit sus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abra un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - vea el archivo [LICENSE.md](LICENSE.md) para más detalles.
