package com.kdnakt.quarkus;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.ReactiveMailer;

@Path("/async")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AsyncMailResource {

    @ConfigProperty(name = "from")
    String to;

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    public CompletionStage<Response> sendASimpleEmailAsync() {
        System.out.println("async!!");
        return reactiveMailer.send(Mail.withText(to, "A reactive email from quarkus", "This is my body"))
                .thenApply(x -> Response.accepted().build());
    }
}