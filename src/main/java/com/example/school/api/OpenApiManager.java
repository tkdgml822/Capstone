package com.example.school.api;

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
    private final String BASE_URL = "https://api.odcloud.kr/api/15086945/v1/uddi:ad8e61a7-bf05-43fd-9091-c2ed8a32e8e1";
    private final String page = "?page=1";
    private final String perPage = "?perPage=10";
    private final String serviceKey = "&serviceKey=data-portal-test-key";


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