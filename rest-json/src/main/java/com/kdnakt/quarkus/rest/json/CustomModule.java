package com.kdnakt.quarkus.rest.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

public class CustomModule extends Module {

    private Version version = new Version(1, 0, 0, null, "com.kdnakt.quarkus", "rest-json");

    @Override
    public String getModuleName() {
        return "CustomModule";
    }

    @Override
    public Version version() {
        return version;
    }

    @Override
    public void setupModule(SetupContext context) {
        // do nothing
    }

}
