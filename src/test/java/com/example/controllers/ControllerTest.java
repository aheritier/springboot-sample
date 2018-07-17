package com.example.controllers;

import com.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.web.reactive.function.BodyInserters.fromMultipartData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void uploadWithAnonymous() {
        webClient
                .post()
                .uri("/api/data/12345/file")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(fromMultipartData("file", new ClassPathResource("/META-INF/file.txt")))
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

    @Test
    @WithMockUser
    public void uploadWithMockUser() {
        webClient
                .post()
                .uri("/api/data/12345/file")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(fromMultipartData("file", new ClassPathResource("/META-INF/file.txt")))
                .exchange()
                .expectStatus()
                .isUnauthorized();
    }

}
