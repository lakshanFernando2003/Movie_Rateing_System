package com.rootcode.movierating.service;

import com.rootcode.movierating.model.Movie;
import com.rootcode.movierating.model.Rating;
import com.rootcode.movierating.storage.MovieStore;

import java.util.*;

public class MovieService {
    private MovieStore Store;

    public MovieService() {
        this.Store = new MovieStore();
    }

    // Method to add a movie
    public boolean addMovie(String MovieTitle, String genre) {
        Movie movie = new Movie(MovieTitle, genre);
        return Store.addMovie(movie);
    }

    // Method to remove a movie
    public boolean removeMovie(String MovieTitle) {
        return Store.removeMovie(MovieTitle);
    }

    // Method to update a movie (genre update only)
    public boolean updateMovie(String MovieTitle, String newGenre) {
        Movie movie = Store.getMovieByTitle(MovieTitle);
        if (movie == null) {
            return false;
        }
        movie.setGenre(newGenre);
        return Store.updateMovie(movie);
    }


    // Method to Get a specific movie
    public Movie getMovie(String MovieTitle) {
        return Store.getMovieByTitle(MovieTitle);
    }

    // Method to Get all movies in a specific genre
    public List<Movie> getMoviesByGenre(String genre) {
        return Store.getMoviesByGenre(genre);
    }


    // Method to add rating to a movie and a review to a movie
    public boolean addRating(String MovieTitle, int stars, String review) {
        Movie movie = Store.getMovieByTitle(MovieTitle);
        if (movie == null) {
            return false;
        }
        Rating rating = new Rating(stars, review);
        movie.addRating(rating);
        return true;
    }

    // Method to get all reviews for a specific movie filtered by star rating
    public List<Rating> getReviewsByRating(String MovieTitle, int star) {
        Movie movie = Store.getMovieByTitle(MovieTitle);
        if (movie == null) {
            return new ArrayList<>();
        }

        return movie.getRatingsByStar(star);
    }

    // Method to get top-rated movies
    public List<Movie> getTopRatedMovies() {
        List<Movie> topMovies = new ArrayList<>();

        // Collect eligible movies (any movie with at least one rating)
        for (Movie movie : Store.getAllMovies()) {
            if (movie.isTopRatedEligible()) {
                topMovies.add(movie);
            }
        }

        // Sort in descending order of average rating
        Collections.sort(topMovies, new Comparator<Movie>() {
            @Override
            public int compare(Movie m1, Movie m2) {
                return Double.compare(m2.getAvarageRating(), m1.getAvarageRating());
            }
        });

        // Return top 5 or fewer if less than 5 movies available
        return topMovies.size() <= 5 ? topMovies : topMovies.subList(0, 5);
    }

    // Method to get all movies
    public Collection<Movie> getAllMovies() {
        return Store.getAllMovies();
    }



}
