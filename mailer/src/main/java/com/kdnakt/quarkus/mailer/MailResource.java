package com.kdnakt.quarkus.mailer;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.Mail;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @GET
    public void sendEmail() {
        // imperative
        mailer.send(Mail.withText("mailaddress",
                "A simple email from quarkus",
                "This is my body."));
    }

}
