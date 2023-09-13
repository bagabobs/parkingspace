package com.wego.parkingspace.adapter.out.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wego.parkingspace.exceptions.TokenGeneratorException;
import org.jooq.tools.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TokenGenerator {
    private final RestTemplate restTemplate;

    public TokenGenerator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public String fetchToken() throws TokenGeneratorException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", "bagaman83@gmail.com");
            jsonObject.put("password", "$$Superbguy2803$$");

            HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate
                    .postForEntity("https://www.onemap.gov.sg/api/auth/post/getToken", request, String.class);
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                throw new TokenGeneratorException("Failed to get token");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            return root.get("access_token").asText();
        } catch (JsonProcessingException jsonProcessingException) {
            throw new TokenGeneratorException(jsonProcessingException.getMessage(), jsonProcessingException);
        }
    }
}
