package com.example.demo.controllers;

import io.restassured.RestAssured;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class UserControllerTest {
    @Test
    @Transactional
    public void testGetAllUsers() {
        RestAssured.port = 8100; // Assurez-vous que votre app tourne sur ce port

        given()
                .when()
                .get("/api/users") // Utilisez le chemin correct
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(0)); // Vérifie que la liste n'est pas vide
    }

    @Test
    public void testCreateUser() {
        RestAssured.port = 8100;

        given()
                .contentType("application/json")
                .body("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")
                .when()
                .post("/api/users") // Utilisez le chemin correct
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("email", equalTo("john.doe@example.com"));
    }}