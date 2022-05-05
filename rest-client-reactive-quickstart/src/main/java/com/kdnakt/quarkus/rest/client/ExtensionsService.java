package com.kdnakt.quarkus.rest.client;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/extensions")
@RegisterRestClient
@RegisterClientHeaders(RequestUUIDHeaderFactory.class)
@ClientHeaderParam(name = "my-header", value = "constant-header-value")
@ClientHeaderParam(name = "computed-header", value = "{com.kdnakt.quarkus.rest.client.Util.computeHeader}")
public interface ExtensionsService {

    @GET
    @ClientHeaderParam(name = "header-from-properties", value = "${header.value}")
    Set<Extension> getById(@QueryParam("id") String id, @HeaderParam("jaxrs-style-header") String headerValue);

    @GET
    CompletionStage<Set<Extension>> getByIdAsync(@QueryParam("id") String id);

    @GET
    Uni<Set<Extension>> getByIdAsUni(@QueryParam("id") String id);

}

