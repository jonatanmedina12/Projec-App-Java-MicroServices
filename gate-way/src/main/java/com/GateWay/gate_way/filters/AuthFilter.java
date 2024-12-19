package com.GateWay.gate_way.filters;


import com.GateWay.gate_way.dto.TokenDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



@Component
public class AuthFilter implements GatewayFilter {
    private final WebClient webClient;

    private static final String  AUTH_VALIDATE_URI = "http://localhost:3010/auth-server/api/v1/auth/validate";

    private static final String ACCESS_TOKEN_HEADER_NAME="accessToken";



    public  AuthFilter(){
        this.webClient = WebClient.builder().build();
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return this.onError(exchange);
        }

        final var tokenHeader = exchange
                .getRequest()
                .getHeaders()
                .get(HttpHeaders.AUTHORIZATION).get(0);

        // Corregir el split aquí
        final var chunks = tokenHeader.split(" "); // Cambiado de "" a " "
        if(chunks.length != 2 || !chunks[0].equals("Bearer")) { // Añadido !
            return this.onError(exchange);
        }

        final var token = chunks[1];

        return this.webClient
                .post()
                .uri(AUTH_VALIDATE_URI)
                .header(ACCESS_TOKEN_HEADER_NAME, token)
                .retrieve()
                .bodyToMono(TokenDto.class)
                .map(response -> exchange)
                .flatMap(chain::filter);
    }

    private Mono<Void>onError(ServerWebExchange exchange){

        final var response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        return response.setComplete();

    }
}