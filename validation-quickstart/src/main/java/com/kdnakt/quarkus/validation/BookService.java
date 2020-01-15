package com.kdnakt.quarkus.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;

@ApplicationScoped
public class BookService {

    public void validateBook(@Valid Book book) {
        // your business logic here
        System.out.println("BookService#validateBook called!");
    }
}