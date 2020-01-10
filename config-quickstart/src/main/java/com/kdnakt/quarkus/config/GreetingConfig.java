package com.kdnakt.quarkus.config;

import io.quarkus.arc.config.ConfigProperties;
import java.util.Optional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "greeting")
public interface GreetingConfig {

    @ConfigProperty(name = "message")
    String message();

    @ConfigProperty(defaultValue = "!")
    String getSuffix();

    Optional<String> getName();
}
