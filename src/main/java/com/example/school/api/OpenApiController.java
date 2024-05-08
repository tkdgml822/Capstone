package com.example.school.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
public class OpenApiController {
    private final OpenApiManager openApiManager;

    @Autowired
    public OpenApiController(OpenApiManager openApiManager) {
        this.openApiManager = openApiManager;
    }

    @GetMapping("/open-api")
    public ResponseEntity<?> fetch() throws UnsupportedEncodingException {
        return success(openApiManager.fetch().getBody());
    }

    private ResponseEntity<?> success(Object body) {
        return ResponseEntity.ok().body(body);
    }
}