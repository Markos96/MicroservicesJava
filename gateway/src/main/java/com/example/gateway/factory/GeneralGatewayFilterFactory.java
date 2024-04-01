package com.example.gateway.factory;

import com.example.gateway.filters.GlobalFilterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GeneralGatewayFilterFactory extends AbstractGatewayFilterFactory<GeneralGatewayFilterFactory.Configuration> {

    public GeneralGatewayFilterFactory() {
        super(Configuration.class);
    }

    private final Logger logger = LoggerFactory.getLogger(GlobalFilterHandler.class);


    @Override
    public GatewayFilter apply(Configuration config) {
       return ((exchange, chain) -> {
           logger.info("Pre-filter execute: " + config.getMessage());

           return chain.filter(exchange).then(Mono.fromRunnable(()->{

                if(!config.getCookieName().isEmpty()){
                    exchange.getResponse()
                            .addCookie(ResponseCookie.from(config.getCookieName(),
                                            config.getCookieValue())
                                    .build());
                }
                logger.info("Post-filter execute: " + config.getMessage());
           }));
       });
    }


    public static class Configuration {
        private String message;
        private String cookieName;
        private String cookieValue;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }
    }
}
