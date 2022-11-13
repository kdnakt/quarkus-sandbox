package com.kdnakt.quarkus;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.jboss.logging.Logger;

import io.smallrye.common.annotation.RunOnVirtualThread;
import io.smallrye.mutiny.Uni;

@Path("")
public class FortuneResource {

    @Inject
    Logger LOG;
    @Inject
    FortuneRepository repo;
    
    @GET
    @Path("/blocking")
    public Fortune blocking() {
        var list = repo.findAllBlocking();
        LOG.infof("(%s) BLOCKING REQUEST!", Thread.currentThread());
        return pickOne(list);
    }

    @GET
    @Path("/reactive")
    public Uni<Fortune> reactive() {
        LOG.infof("(%s) REACTIVE REQUEST!", Thread.currentThread());
        return repo.findAllAsync()
                .map(this::pickOne);
    }
    
    @GET
    @Path("/virtual")
    @RunOnVirtualThread
    public Fortune virtualThread() {
        var list = repo.findAllAsyncAndAwait();
        LOG.infof("(%s) VIRTUAL THREAD REQUEST!", Thread.currentThread());
        return pickOne(list);
    }

    private Fortune pickOne(List<Fortune> list) {
        return list.get(0);
    }

    public Uni<List<String>> getQuotesAsync(int size) {
        return Uni.createFrom().item(List.of(
            "Quote 1",
            "Quote 2",
            "Quote 3"
        ));
    }

    @GET
    @Path("/quoted-blocking")
    public List<Fortune> getAllQuotedBlocking() {
        // we get the list of fortunes
        var fortunes = repo.findAllBlocking();

        // we get the list of quotes
        var quotes = getQuotesAsync(fortunes.size()).await().indefinitely();

        // we append each quote to each fortune
        for (int i = 0; i < fortunes.size(); i++) {
            fortunes.get(i).title += " - " + quotes.get(i);
        }
        return fortunes;
    }

}