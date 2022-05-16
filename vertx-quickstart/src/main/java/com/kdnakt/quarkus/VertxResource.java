package com.kdnakt.quarkus;

import io.vertx.mutiny.core.Vertx;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("/vertx")
public class VertxResource {

    private final Vertx vertx;

    @Inject
    public VertxResource(Vertx vertx) {
        this.vertx = vertx;
    }

}

