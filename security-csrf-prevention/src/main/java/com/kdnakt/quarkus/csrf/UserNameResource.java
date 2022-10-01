package com.kdnakt.quarkus.csrf;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/service")
public class UserNameResource {

    @Inject
    Template csrfToken;

    @GET
    @Path("/csrfTokenForm")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance form() {
        return csrfToken.instance();
    }

    @POST
    @Path("/csrfTokenForm")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String post(@FormParam("name") String name) {
        return name;
    }
}
