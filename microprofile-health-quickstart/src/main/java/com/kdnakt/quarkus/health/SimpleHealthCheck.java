package com.kdnakt.quarkus.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {

    boolean status = false;

    @Override
    public HealthCheckResponse call() {
        status = !status;
        if (status) {
            return HealthCheckResponse.up("Simple health check");
        } else {
            return HealthCheckResponse.down("Simple health check");
        }
    }
}