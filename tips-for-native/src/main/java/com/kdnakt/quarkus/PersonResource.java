package com.kdnakt.quarkus;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final Jsonb jsonb;

    public PersonResource() {
        jsonb = JsonbBuilder.create(new JsonbConfig());
    }

    @GET
    public Response list() {
        return Response.ok(jsonb.fromJson("{\"first\":  \"foo\", \"last\":  \"bar\"}", Person.class)).build();
    }
}