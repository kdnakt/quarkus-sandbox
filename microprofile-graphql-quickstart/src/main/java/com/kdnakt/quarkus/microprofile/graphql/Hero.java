package com.kdnakt.quarkus.microprofile.graphql;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    public String name;
    public String surname;
    public Double height;
    public Integer mass;
    public Boolean darkSide;
    public LightSaber lightSaber;
    public List<Integer> episodeIds = new ArrayList<>();
}

enum LightSaber {
    RED, BLUE, GREEN
}

