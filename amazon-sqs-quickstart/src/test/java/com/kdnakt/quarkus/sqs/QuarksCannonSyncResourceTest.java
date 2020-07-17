package com.kdnakt.quarkus.sqs;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class QuarksCannonSyncResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/sync-cannon")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}