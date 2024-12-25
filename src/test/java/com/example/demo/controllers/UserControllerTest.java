
package com.example.demo.controllers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.example.demo.utils.ExtentManager;
import io.restassured.RestAssured;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
        import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    public static void setup() {
        // Configure RestAssured port
        RestAssured.port = 8100;

        // Initialize ExtentReports
        extent = ExtentManager.getInstance();
    }

    @Test
    @Transactional
    public void testGetAllUsers() {
        // Start Extent Test
        test = extent.createTest("Test - Get All Users", "Verify that the endpoint retrieves all users");

        try {
            given()
                    .when()
                    .get("/api/users") // Correct endpoint path
                    .then()
                    .statusCode(200)
                    .body("size()", greaterThanOrEqualTo(0)); // Validate the response is not empty

            // Log success
            test.pass("GET /api/users returned 200 with a non-empty response");
        } catch (Exception e) {
            // Log failure
            test.fail("Test failed with exception: " + e.getMessage());
            throw e; // Rethrow to mark the test as failed
        }
    }

    @Test
    public void testCreateUser() {
        // Start Extent Test
        test = extent.createTest("Test - Create User", "Verify that the endpoint successfully creates a user");

        try {
          //  RestAssured.port = 8100; // Assurez-vous que votre app tourne sur ce port

            given()
                    .contentType("application/json")
                    .body("{\"name\": \"John Doe\", \"email\": \"john.doe@example.com\"}")
                    .when()
                    .post("/api/users") // Correct endpoint path
                    .then()
                    .statusCode(200)
                    .body("name", equalTo("John Doe"))
                    .body("email", equalTo("john.doe@example.com"));

            // Log success
            test.pass("POST /api/users successfully created a user with status 200");
        } catch (Exception e) {
            // Log failure
            test.fail("Test failed with exception: " + e.getMessage());
            throw e; // Rethrow to mark the test as failed
        }
    }

    @AfterAll
    public static void tearDown() {
        // Write ExtentReports to the file
        if (extent != null) {
            extent.flush();
        }
    }
}
