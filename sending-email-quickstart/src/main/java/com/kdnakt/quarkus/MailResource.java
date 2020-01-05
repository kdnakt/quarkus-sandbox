package com.kdnakt.quarkus;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.ReactiveMailer;

public class MailResource {

    private static String TO = "";

    @Inject
    private Mailer mailer;

    @Inject
    private ReactiveMailer reactiveMailer;

    @GET
    @Path("/simple")
    public Response sendASimpleEmail() {
        mailer.send(Mail.withText(TO, "A simple email from quarkus", "This is my body"));
        return Response.accepted().build();
    }

    @GET
    @Path("/async")
    public CompletionStage<Response> sendASimpleEmailAsync() {
        return reactiveMailer.send(Mail.withText(TO, "A reactive email from quarkus", "This is my body"))
                .thenApply(x -> Response.accepted().build());
    }
}