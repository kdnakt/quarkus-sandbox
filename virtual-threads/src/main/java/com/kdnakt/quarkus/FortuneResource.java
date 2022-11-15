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

    @GET
    @Path("/quoted-reactive")
    public Uni<List<Fortune>> getAllQuotedReactive() {
        // we first fetch the list of resource and we memoize it
        // to avoid fetching it again everytime need it
        var fortunes = repo.findAllAsync().memoize().indefinitely();

        // once we get a result for fortunes,
        // we know its size and can thus query the right number of quotes
        var quotes = fortunes.onItem().transformToUni(list -> getQuotesAsync(list.size()));

        // we now need to combine the two reactive streams
        // before returning the result to the user
        return Uni.combine().all().unis(fortunes,quotes).asTuple().onItem().transform(tuple -> {
            var todoList=tuple.getItem1();
            //can await it since it is already resolved
            var quotesList = tuple.getItem2();
            for(int i=0; i  < todoList.size();i ++){
                            todoList.get(i).title+= "   -  "+quotesList.get(i);
            }
            return todoList;
        });
    }

    @GET
    @RunOnVirtualThread
    @Path("/quoted-virtual-thread")
    public List<Fortune> getAllQuotedVirtualThread() {
        //we get the list of fortunes
        var fortunes = repo.findAllAsyncAndAwait();

        //we get the list of quotes
        var quotes = getQuotesAsync(fortunes.size()).await().indefinitely();

        //we append each quote to each fortune
        for(int i=0; i  < fortunes.size();i ++){
            fortunes.get(i).title+= "   -  "+quotes.get(i);
        }
        return fortunes;
    }
}