package com.eidiko.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                    .route(predicateSpec -> predicateSpec.path("/customer/product/**", "/customer/**")
                            .uri("lb://CUSTOMER-SERVICE"))
                    .route(predicateSpec -> predicateSpec.path("/product/**")
                            .uri("lb://PRODUCT-SERVICE"))
                .build();
    }

}
