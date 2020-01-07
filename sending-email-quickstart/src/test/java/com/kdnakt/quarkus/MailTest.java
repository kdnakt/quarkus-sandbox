package com.kdnakt.quarkus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MailTest {

    @ConfigProperty(name = "from")
    String to;

    @Inject
    MockMailbox mailbox;

    @BeforeEach
    void init() {
        mailbox.clear();
    }

    @Test
    void testTextMail() throws IOException {
        // call a REST endpoint that sends email
        given().when().get("/simple").then().statusCode(202).body(is("OK"));

        // verify that it was sent
        List<Mail> sent = mailbox.getMessagesSentTo(to);
        assertThat(sent).hasSize(1);
        Mail actual = sent.get(0);
        assertThat(actual.getText()).isEqualTo("This is my body");
        assertThat(actual.getSubject()).isEqualTo("A simple email from quarkus");

        assertThat(mailbox.getTotalMessagesSent()).isEqualTo(1);
    }
}