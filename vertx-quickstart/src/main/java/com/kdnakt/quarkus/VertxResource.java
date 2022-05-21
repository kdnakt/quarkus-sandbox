package com.kdnakt.quarkus;

import java.nio.charset.StandardCharsets;

import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.ext.web.client.HttpResponse;
import io.vertx.mutiny.ext.web.client.WebClient;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

@Path("/vertx")
public class VertxResource {

    private final Vertx vertx;
    private final WebClient client;

    @Inject
    private EventBus bus;

    @Inject
    public VertxResource(Vertx vertx) {
        this.vertx = vertx;
        this.client = WebClient.create(vertx);
    }

    @GET
    @Path("/lorem")
    public Uni<String> readShortFile() {
        return vertx.fileSystem().readFile("lorem.txt")
                .onItem().transform(content -> content.toString(StandardCharsets.UTF_8));
    }

    @GET
    @Path("/book")
    public Multi<String> readLargeFile() {
        return vertx.fileSystem().open("book.txt", new OpenOptions().setRead(true))
                .onItem().transformToMulti(file -> file.toMulti())
                .onItem().transform(content -> content.toString(StandardCharsets.UTF_8)
                        + "\n----------\n");
    }

    @GET
    @Path("/hello")
    public Uni<String> hello(@QueryParam("name") String name) {
        return bus.<String>request("greetings", name)
                .onItem().transform(response -> response.body());
    }

    private static final String URL = "https://en.wikipedia.com/w/api.php"
            + "?action=parse&page=Quarkus&format=json&prop=langlinks";

    @GET
    @Path("/web")
    public Uni<JsonArray> retrieveDataFromWikipedia() {
        return client.getAbs(URL).send()
                .onItem().transform(HttpResponse::bodyAsJsonObject)
                .onItem().transform(json -> json.getJsonObject("parse")
                        .getJsonArray("langlinks"));
    }

}

