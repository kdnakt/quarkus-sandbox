package com.kdnakt.quarkus;

public class Fortune {

    public String title;

    public Fortune(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
