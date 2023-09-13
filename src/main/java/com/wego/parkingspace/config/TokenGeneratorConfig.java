package com.wego.parkingspace.config;

import com.wego.parkingspace.adapter.out.web.TokenGenerator;
import com.wego.parkingspace.exceptions.TokenGeneratorException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TokenGeneratorConfig {
    private final RestTemplate restTemplate;

    public TokenGeneratorConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public TokenString tokenGenerator() throws TokenGeneratorException {
        TokenGenerator tokenGenerator = new TokenGenerator(restTemplate);
        TokenString tokenString = new TokenString();
        tokenString.setToken(tokenGenerator.fetchToken());
        return tokenString;
    }
}
