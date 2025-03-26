package com.rootcode.movierating;

import com.rootcode.movierating.cli.MovieRatingSystemCLI;

public class Main {
    public static void main(String[] args) {
        MovieRatingSystemCLI cli = new MovieRatingSystemCLI();
        cli.selectOption();
    }

}
