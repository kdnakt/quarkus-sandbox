package com.kdnakt.quarkus.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kdnakt.quarkus.sqs.model.Quark;

import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Path("/async/cannon")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuarksCannonAsyncResource {

    private static final Logger LOGGER = Logger.getLogger(QuarksCannonAsyncResource.class);

    @Inject
    SqsAsyncClient sqs;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    static ObjectWriter QUARK_WRITER = new ObjectMapper().writerFor(Quark.class);

    @POST
    @Path("/shoot")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> sendMessage(Quark quark) throws Exception {
        String message = QUARK_WRITER.writeValueAsString(quark);
        return Uni.createFrom()
            .completionStage(sqs.sendMessage(m -> m.queueUrl(queueUrl).messageBody(message)))
            .onItem().invoke(item -> LOGGER.infov("Fired Quark[{0}, {1}}]", quark.getFlavor(), quark.getSpin()))
            .onItem().apply(SendMessageResponse::messageId)
            .onItem().apply(id -> Response.ok().entity(id).build());
    }
}