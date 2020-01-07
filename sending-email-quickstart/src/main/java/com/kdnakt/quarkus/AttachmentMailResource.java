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

@Path("/attachment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttachmentMailResource {

    @ConfigProperty(name = "from")
    String to;

    @Inject
    Mailer mailer;

    @GET
    public Response sendEmailWithAttachment() {
        System.out.println("attachment!!");
        mailer.send(Mail.withText(to, "An email from quarkus with attachment", "This is my body")
                .addAttachment("my-file.txt", "content of my file".getBytes(), "text/plain"));
        return Response.accepted().build();
    }

}