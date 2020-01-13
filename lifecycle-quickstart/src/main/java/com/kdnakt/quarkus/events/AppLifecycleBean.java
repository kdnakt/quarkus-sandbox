package com.kdnakt.quarkus.events;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    LaunchMode mode;

    void onStart(@Observes StartupEvent ev) {               
        LOGGER.info("The application is starting..." + mode.name());
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOGGER.info("The application is stopping..." + LaunchMode.current());
    }

}