package com.kdnakt.quarkus;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.ReactiveMailer;

public class MailResource {

    @ConfigProperty(name = "from")
    private String to;

    @Inject
    private Mailer mailer;

    @Inject
    private ReactiveMailer reactiveMailer;

    @GET
    @Path("/simple")
    public Response sendASimpleEmail() {
        System.out.println("simple!");
        mailer.send(Mail.withText(to, "A simple email from quarkus", "This is my body"));
        return Response.accepted().build();
    }

    @GET
    @Path("/async")
    public CompletionStage<Response> sendASimpleEmailAsync() {
        return reactiveMailer.send(Mail.withText(to, "A reactive email from quarkus", "This is my body"))
                .thenApply(x -> Response.accepted().build());
    }
}