package com.kdnakt.quarkus;

import java.io.File;

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

@Path("/html")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HtmlMailResource {

    @ConfigProperty(name = "from")
    String to;

    @Inject
    Mailer mailer;

    @GET
    public Response sendingHTML() {
        System.out.println("html!!");
        String body = "<strong>Hello!</strong>" + "\n"
                + "<p>Here is an image for you: <img src=\"cid:my-image@quarkus.io\"/></p>" + "<p>Regards</p>";

        mailer.send(Mail.withHtml(to, "An email in HTML", body).addInlineAttachment("quarkus-logo.png",
                new File("classes/META-INF/resources/quarkus-logo.png"), "image/png", "<my-image@quarkus.io>"));
        return Response.accepted().build();
    }

}