package com.MicroServicios.companies_crud.controller;


import com.MicroServicios.companies_crud.entities.Company;
import com.MicroServicios.companies_crud.services.CompanyService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "company")
@Tag(name = "Company Controller", description = "API para gestionar compañías")

public class CompanyController {
    @Autowired
    private   CompanyService  companyService;

    @Operation(summary = "Obtener compañía por nombre")
    @ApiResponse(responseCode = "200", description = "Compañía encontrada")
    @ApiResponse(responseCode = "404", description = "Compañía no encontrada")
    @GetMapping(path = "{name}")
    @Observed(name = "company.name")
    @Timed(value = "company.name")
    public ResponseEntity<Company>get(@PathVariable String name){


        return ResponseEntity.ok(this.companyService.readByName(name));

    }
    @Operation(summary = "Crear nueva compañía")
    @ApiResponse(responseCode = "201", description = "Compañía creada exitosamente")
    @PostMapping
    public ResponseEntity<Company>post(@RequestBody Company company){

        return  ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();


    }

    @Operation(summary = "Actualizar compañía existente",
            description = "Actualiza una compañía existente basado en su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Compañía actualizada exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))),
            @ApiResponse(responseCode = "404",
                    description = "Compañía no encontrada"),
            @ApiResponse(responseCode = "400",
                    description = "Datos de compañía inválidos")
    })
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> put(
            @Parameter(description = "Nueva información de la compañía")
            @RequestBody Company company,
            @Parameter(description = "Nombre de la compañía a actualizar")
            @PathVariable String name) {
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @Operation(summary = "Eliminar compañía",
            description = "Elimina una compañía basado en su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Compañía eliminada exitosamente"),
            @ApiResponse(responseCode = "404",
                    description = "Compañía no encontrada")
    })


    @DeleteMapping(path = "{name}")
    public ResponseEntity<?> delete(
            @Parameter(description = "Nombre de la compañía a eliminar")
            @PathVariable String name) {
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
