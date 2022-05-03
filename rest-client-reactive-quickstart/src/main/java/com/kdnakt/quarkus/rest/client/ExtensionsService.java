package com.kdnakt.quarkus.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Set;

@Path("/extensions")
@RegisterRestClient
public interface ExtensionsService {

    @GET
    Set<Extension> getById(@QueryParam("id") String id);

}

