package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilterHandler implements GlobalFilter{

    private final Logger logger = LoggerFactory.getLogger(GlobalFilterHandler.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Pre-filter execute: ");
        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            logger.info("Post-filter execute: ");
            exchange.getResponse().getCookies().add("color", ResponseCookie
                    .from("red")
                    .build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }
}
