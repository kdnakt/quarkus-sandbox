package com.kdnakt.quarkus.rest.client;

public class Util {

    public static String computeHeader() {
        return String.valueOf(System.currentTimeMillis());
    }
}

