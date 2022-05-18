package com.kdnakt.quarkus;

import java.nio.charset.StandardCharsets;

import io.vertx.mutiny.core.Vertx;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;

@Path("/vertx")
public class VertxResource {

    private final Vertx vertx;

    @Inject
    public VertxResource(Vertx vertx) {
        this.vertx = vertx;
    }

    @GET
    @Path("/lorem")
    public Uni<String> readShortFile() {
        return vertx.fileSystem().readFile("lorem.txt")
                .onItem().transform(content -> content.toString(StandardCharsets.UTF_8));
    }

}

