package com.kdnakt.quarkus.cache;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class WeatherForecastService {

    @CacheResult(cacheName = "weather-cache")
    public String getDailyForecast(@CacheKey LocalDate date, @CacheKey String city) {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return date.getDayOfWeek() + " will be " + getDailyResult(date.getDayOfMonth() % 4) + " in " + city;
    }

    @CacheInvalidate(cacheName ="weather-cache")
    public void invalidate(@CacheKey LocalDate date, @CacheKey String city) {
        System.out.println("invalidating ...");
        System.out.println(city + ": " + date);
    }

    private String getDailyResult(int dayOfMonthModuloFour) {
        switch (dayOfMonthModuloFour) {
            case 0:
                return "sunny";
            case 1:
                return "cloudy";
            case 2:
                return "chilly";
            case 3:
                return "rainy";
            default:
                throw new IllegalArgumentException();
        }
    }
}