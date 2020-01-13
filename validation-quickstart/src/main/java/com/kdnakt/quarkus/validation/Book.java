package com.kdnakt.quarkus.validation;

import javax.validation.constraints.*;

public class Book {

    @NotBlank(message = "Title may not be blank")
    public String title;

    @NotBlank(message = "Author may not be blank")
    public String author;

    @Min(message = "Author has been very lazy", value = 1)
    public double pages;
}