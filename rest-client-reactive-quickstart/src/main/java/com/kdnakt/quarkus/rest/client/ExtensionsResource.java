package com.kdnakt.quarkus.rest.client;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Set;

@Path("/extension")
public class ExtensionsResource {

    @RestClient
    ExtensionsService extensionsService;

    @GET
    @Path("/id/{id}")
    @Blocking
    public Set<Extension> id(String id) {
        return extensionsService.getById(id);
    }

}

