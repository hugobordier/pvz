package com.epf.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CoursEpfBack")
public class SwaggerDocsController {

    @GetMapping("/openapi.json")
    public String getOpenApi() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("openapi.json");
        if (inputStream == null) {
            throw new RuntimeException("Fichier openapi.json introuvable !");
        }
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}

