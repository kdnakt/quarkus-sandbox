package com.kdnakt.quarkus;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Uni;

@Path("")
public class FortuneResource {

    @Inject
    FortuneRepository repo;
    
    @GET
    @Path("/blocking")
    public Fortune blocking() {
        var list = repo.findAllBlocking();
        return pickOne(list);
    }

    @GET
    @Path("/reactive")
    public Uni<Fortune> reactive() {
        return repo.findAllAsync()
                .map(this::pickOne);
    }
    
    @GET
    @Path("/virtual")
    @RunOnVirtualThread
    public Fortune virtualThread() {
        var list = repo.findAllAsyncAndAwait();
        return pickOne(list);
    }

    private Fortune pickOne(List<Fortune> list) {
        return list.get(0);
    }

}