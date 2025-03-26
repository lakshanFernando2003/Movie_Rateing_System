package com.rootcode.movierating.model;

public class Rating {

    private int stars; // rating between 1 to 5
    private String review; // review of the movie


    // Constructor
    public Rating(int stars, String review) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Error ~ Rating must be between 1 to 5!");
        }
        this.stars = stars;
        this.review = review;
    }


    // Method to process stars to print *****
    public String processedStars() {
        switch (stars) {
            case 1: return "*";
            case 2: return "**";
            case 3: return "***";
            case 4: return "****";
            case 5: return "*****";
            default: return null; // This should never happen due to validation in constructor and setter
        }
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        if (stars < 1 || stars > 5) {
            throw new IllegalArgumentException("Error ~ Rating must be between 1 to 5!");
        }
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString(){
        return  "Rating: " + stars + " stars - " + processedStars() +
                (review == null || review.isEmpty() ? "No review" : review); // checking for no review
    }
}
