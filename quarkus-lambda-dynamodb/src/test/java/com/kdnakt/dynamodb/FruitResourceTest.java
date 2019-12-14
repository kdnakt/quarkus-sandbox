package com.kdnakt.dynamodb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class FruitResourceTest {

    private static final String NAME = "orange";
    private static final String DESCRIPTION = "this is an orange";

    @Test
    public void testGetFruitsEndpoint() throws JsonProcessingException {
        given()
          .when().get("/fruits")
          .then()
             .statusCode(200)
             .body(containsString(NAME), containsString(DESCRIPTION));
    }

    @Test
    public void testPostFruitsEndpoint() throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        final Fruit orange = new Fruit();
        orange.setName(NAME);
        orange.setDescription(DESCRIPTION);
        given()
            .header("Content-Type", "application/json")
            .and()
            .body(mapper.writeValueAsString(orange))
            .when().post("/fruits")
            .then().statusCode(200);
    }

}