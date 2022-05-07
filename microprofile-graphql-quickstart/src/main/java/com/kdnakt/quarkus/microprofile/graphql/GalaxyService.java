package com.kdnakt.quarkus.microprofile.graphql;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class GalaxyService {

    private List<Hero> heroes = new ArrayList<>();

    private List<Film> films = new ArrayList<>();

    public GalaxyService() {
        Film aNewHope = new Film();
        aNewHope.title = "A New Hope";
        aNewHope.releaseDate = LocalDate.of(1977, Month.MAY, 25);
        aNewHope.episodeID = 4;
        aNewHope.director = "George Lucas";

        films.add(aNewHope);

        Hero luke = new Hero();
        luke.name = "Luke";
        luke.surname = "Skywalker";
        luke.height = 1.7;
        luke.mass = 73;
        luke.lightSaber = LightSaber.GREEN;
        luke.darkSide = false;
        luke.episodeIds.addAll(Arrays.asList(4, 5, 6));

        heroes.add(luke);
    }

}
