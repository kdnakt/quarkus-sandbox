package com.kdnakt.quarkus.mailer;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.quarkus.mailer.Mail;
import io.smallrye.mutiny.Uni;

@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    public void sendEmail() {
        // imperative
        mailer.send(Mail.withText("mailaddress",
                "A simple email from quarkus",
                "This is my body."));
    }

    @GET
    @Path("/reactive")
    public Uni<Void> sendEmailUsingReactiveMailer() {
        return reactiveMailer.send(Mail.withText("foo@example.com",
                "A simple email from quarkus",
                "This is my body using the reactive API."));
    }

}
