package com.microservicers.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rota para /get que adiciona um cabeçalho e um parâmetro de requisição
                .route(p -> p
                        .path("/get")
                        .filters(f -> f
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                // Rota para currency-exchange
                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))
                // Rota para currency-conversion
                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))
                .build();
    }
}
