package com.kdnakt.quarkus.sqs;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import software.amazon.awssdk.http.Header;

import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.UUID;

import com.kdnakt.quarkus.sqs.model.Quark;

@QuarkusTest
public class QuarksCannonSyncResourceTest {

    @Test
    public void testHelloEndpoint() {
        Quark q = new Quark();
        q.setFlavor("flavor");
        q.setSpin("spin");
        given()
          .when()
            .contentType(ContentType.JSON)
            .body(q)
            .post("/sync/cannon/shoot")
          .then()
             .statusCode(200)
             .body(MatchesPattern.matchesPattern("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})"));
    }

}