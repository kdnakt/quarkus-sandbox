package com.kdnakt.dynamodb;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FruitResourceTest {

    @Test
    public void testGetFruitsEndpoint() {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(is("[{\"description\":\"this is an orange\",\"name\":\"orange\"}]"));
    }

    @Test
    public void testPostFruitsEndpoint() {
        given()
            .header("Content-Type", "application/json")
            .and()
            .body("{\"name\": \"orange\", \"description\": \"this is an orange\"}")
            .when().post("/fruits")
            .then().statusCode(200);
    }

}