package com.eazybytes.gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

//    @Autowired
//    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p.path("/eazybank/accounts/**")
//                        .filters(f -> f.filters(filterFactory.apply())
//                                .rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}")
//                                .removeRequestHeader("Cookie"))
//                        .uri("lb://ACCOUNTS"))
//                .route(p -> p.path("/eazybank/loans/**")
//                        .filters(f -> f.filters(filterFactory.apply())
//                                .rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}")
//                                .removeRequestHeader("Cookie"))
//                        .uri("lb://LOANS"))
//                .route(p -> p
//                        .path("/eazybank/cards/**")
//                        .filters(f -> f.filters(filterFactory.apply())
//                                .rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}")
//                                .removeRequestHeader("Cookie"))
//                        .uri("lb://CARDS")).build();

        return builder.routes()
                .route(p -> p
                        .path("/eazybank/accounts/**")
                        .filters(f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time",new Date().toString()))
                        .uri("lb://ACCOUNTS")).
                        route(p -> p
                                .path("/eazybank/loans/**")
                                .filters(f -> f.rewritePath("/eazybank/loans/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time",new Date().toString()))
                                .uri("lb://LOANS")).
                        route(p -> p
                                .path("/eazybank/cards/**")
                                .filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)","/${segment}")
                                        .addResponseHeader("X-Response-Time",new Date().toString()))
                                .uri("lb://CARDS")).build();
    }

}
