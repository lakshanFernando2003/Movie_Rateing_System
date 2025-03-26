package com.rootcode.movierating.storage;

import com.rootcode.movierating.model.Movie;
import java.util.*;
public class MovieStore {
    private Map<String,Movie> movieMap;

    public MovieStore() {
        this.movieMap = new HashMap<>();
    }

    // Method to add a movie to the store
    public boolean addMovie(Movie movie) {
        String MovieTitle = movie.getTitle().toLowerCase();

        if (movieMap.containsKey(MovieTitle)) {
            return false;
        }
        movieMap.put(MovieTitle, movie);
        return true;
    }

    // Method to remove a movie from the store
    public boolean removeMovie(String title) {
        return movieMap.remove(title.toLowerCase()) != null;
    }

    // Update movie => should replace the whole movie object
    public  boolean updateMovie(Movie updatedMovie) {
        String MovieTitle = updatedMovie.getTitle().toLowerCase();
        if (!movieMap.containsKey(MovieTitle)) {
            return false;
        }
        movieMap.put(MovieTitle, updatedMovie);
        return true;
    }

    // Method to get a movie by title
    public Movie getMovieByTitle(String title) {
        return movieMap.get(title.toLowerCase());
    }

    // Method to get all movies
    public Collection<Movie> getAllMovies() {
        return movieMap.values();
    }

    // Method to get movies by genre
    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> result = new ArrayList<>();
        for (Movie movie : movieMap.values()) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                result.add(movie);
            }
        }
        return result;
    }

    //  Method to Check if movies are exist
    public boolean exists(String MovieTitle) {
        return movieMap.containsKey(MovieTitle.toLowerCase());
    }




}
