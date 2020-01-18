package com.kdnakt.quarkus.restclient.multipart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MultipartClientResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().post("/client/multipart")
            .then()
            .statusCode(200)
            .body(containsString("Content-Disposition: form-data; name=\"file\""),
                    containsString("HELLO WORLD"),
                    containsString("Content-Disposition: form-data; name=\"fileName\""),
                    containsString("greeting.txt"));
    }

}