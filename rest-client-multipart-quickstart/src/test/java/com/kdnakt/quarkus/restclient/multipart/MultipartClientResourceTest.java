package com.kdnakt.quarkus.restclient.multipart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MultipartClientResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/client")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}