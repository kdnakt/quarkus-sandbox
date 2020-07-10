package com.kdnakt.quarkus.s3;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class S3SyncClientResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/s3")
            .then()
            .statusCode(200)
            .body(is(notNullValue()));
    }

}