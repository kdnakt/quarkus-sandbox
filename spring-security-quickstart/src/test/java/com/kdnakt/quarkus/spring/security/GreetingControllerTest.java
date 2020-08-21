package com.kdnakt.quarkus.spring.security;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingControllerTest {

    @Test
    public void testHelloEndpointForbidden() {
        given().auth().preemptive().basic("stuart", "test")
                .when().get("/greeting")
                .then()
                .statusCode(403);
    }

    @Test
    public void testHelloEndpoint() {
        given().auth().preemptive().basic("scott", "jb0ss")
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }

}