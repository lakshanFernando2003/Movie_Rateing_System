package com.rootcode.movierating.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private String genre; // fixed types only ~ Horror, Action, Romantic, Comedy, Science Fiction
    private List <Rating> ratings;

    public Movie(String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }

    // Method to get the average rating of the movie
    public double getAvarageRating() {
        if (ratings.isEmpty()) return 0.0;

        int total = 0;
        for  (Rating rating : ratings) {
            total += rating.getStars();
        }
        return total / (double) ratings.size();
    }

     // Method to get the top rated movie
    public boolean isTopRatedEligible(){
        return ratings.size() > 0; // A movie must have at least 1 rating to be eligible for top rated
    }

    public List<Rating> getRatingsByStar (int star){
        List<Rating> filteredRating = new ArrayList<>();
        for (Rating rating : ratings) {
            if (rating.getStars() == star) {
                filteredRating.add(rating);
            }
        }
        return filteredRating;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Avg Rating: " + String.format("%.2f", getAvarageRating());
    }

}
