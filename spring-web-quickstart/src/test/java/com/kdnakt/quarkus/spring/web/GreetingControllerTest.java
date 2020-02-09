package com.kdnakt.quarkus.spring.web;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/greeting/quarkus")
          .then()
            .statusCode(200)
            .body("message", is("hello quarkus"));
    }

}