package com.kdnakt.quarkus.grpc;

import io.quarkus.example.GreeterGrpc;
import io.quarkus.example.HelloRequest;
import io.quarkus.grpc.runtime.annotations.GrpcService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    @Inject
    @GrpcService("hello")
    GreeterGrpc.GreeterBlockingStub client;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/{name}/{age}")
    public String hello(@PathParam("name") String name,
            @PathParam("age") int age) {
        return client.sayHello(
                HelloRequest.newBuilder().setName(name).setAge(age).build()
                ).getMessage();
    }

}
