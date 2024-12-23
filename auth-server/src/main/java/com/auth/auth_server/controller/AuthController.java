package com.auth.auth_server.controller;

import com.auth.auth_server.dto.LoginRequestDto;
import com.auth.auth_server.dto.TokenDto;
import com.auth.auth_server.dto.UserDto;
import com.auth.auth_server.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticación", description = "Endpoints para manejo de autenticación")

@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Login de usuario",
            description = "Autenticar usuario y generar token JWT")
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @Operation(summary = "Validar token",
            description = "Validar token JWT existente")
    @ApiResponse(responseCode = "200", description = "Token válido")
    @ApiResponse(responseCode = "401", description = "Token inválido o expirado")
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateToken(
            @RequestHeader(name = "Authorization", required = true) String authorization
    ) {
        String token = extractToken(authorization);
        TokenDto tokenDto = authService.validateToken(
                TokenDto.builder()
                        .accessToken(token)
                        .build()
        );
        return ResponseEntity.ok(tokenDto);
    }

    private String extractToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        throw new IllegalArgumentException("Token no proporcionado o formato inválido");
    }
    @Operation(summary = "Registro de usuario",
            description = "Crear una nueva cuenta de usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario ya existe")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody UserDto userDto
    ) {
        UserDto createdUser = authService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}