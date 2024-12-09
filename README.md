# Companies CRUD API

API REST para la gestión de compañías y sus sitios web asociados.

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3.2.1
- PostgreSQL
- Spring Data JPA
- Swagger/OpenAPI

## Requisitos Previos

- Java JDK 21
- Maven 3.9.5+
- PostgreSQL 15+

## Configuración de Base de Datos

1. Crear una base de datos en PostgreSQL:
```
sql
-- table company
create table if not exists company(
                                      id serial primary key,
                                      "name" varchar(32) not null unique,
    founder varchar(128),
    logo varchar(255),
    foundation_date date
    );

-- index for name
create index name_company
    on company("name");

-- table web site
create table if not exists web_site(
                                       id serial primary key,
                                       id_company bigint,
                                       "name" varchar(32) not null unique,
    category varchar(32) not null,
    description text,
    constraint fk_company
    foreign key(id_company)
    references company(id)
    on delete cascade
    );
```
## Configurar las credenciales en application.properties:
```
  spring.datasource.url=jdbc:postgresql://localhost:5432/companies
  spring.datasource.username=tu_usuario
  spring.datasource.password=tu_contraseña
```
## Instalación y Ejecución
```
git clone 

```
## Compilar el proyecto:
```
mvn clean install
```

## Documentación API

```
http://localhost:8080/companies-crud/swagger-ui.html

```
## Endpoints/Compañías

```
GET /company/{name} - Obtener compañía por nombre
POST /company - Crear nueva compañía
PUT /company/{name} - Actualizar compañía existente
DELETE /company/{name} - Eliminar compañía
```
## Manejo de Errores
La API incluye manejo de errores para:
Duplicados
Recursos no encontrados
Errores de validación
Errores internos del servidor

## Seguridad

Validación de datos de entrada
Manejo de excepciones global
Restricciones de base de datos

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE.md para más detalles.
