package com.kdnakt.quarkus.rest.client;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.UUID;

@ApplicationScoped
public class RequestUUIDHeaderFactory implements ClientHeadersFactory {

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incoming,
            MultivaluedMap<String, String> outgoing) {
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.add("X-request-uuid", UUID.randomUUID().toString());
        return result;
    }
}

