package com.kdnakt.quarkus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@Path("/simple")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MailResource {

    @ConfigProperty(name = "from")
    String to;

    @Inject
    Mailer mailer;

    @GET
    public Response sendASimpleEmail() {
        System.out.println("simple!!");
        mailer.send(Mail.withText(to, "A simple email from quarkus", "This is my body"));
        return Response.accepted("OK").build();
    }

}