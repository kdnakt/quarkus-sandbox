package com.kdnakt.quarkus.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(
                 containsString("Apple"), containsString("Winter fruit"),
                 containsString("Pineapple"), containsString("Tropical fruit"));
    }

}