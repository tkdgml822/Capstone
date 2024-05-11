package com.example.school.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;


@Component
public class OpenApiManager {
    @Value("${API_URL}")
    private String BASE_URL;

    private String page = "?page=1";
    private String perPage = "?perPage=10";

    @Value("${API_KEY}")
    private String serviceKey;


    private String makeUrl() throws UnsupportedEncodingException {
        return BASE_URL +
                page +
                perPage +
                serviceKey;
    }

    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        System.out.println(makeUrl());
        RestTemplate restTemplate = new RestTemplate(); 
        HttpEntity<?> entity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Map> resultMap = restTemplate.exchange(makeUrl(), HttpMethod.GET, entity, Map.class);
        System.out.println(resultMap.getBody());
        return resultMap;
    }
}