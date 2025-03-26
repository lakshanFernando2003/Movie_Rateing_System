package com.rootcode.movierating.cli;

import com.rootcode.movierating.model.Movie;
import com.rootcode.movierating.model.Rating;
import com.rootcode.movierating.service.MovieService;

import java.util.List;
import java.util.Scanner;

public class MovieRatingSystemCLI {

    private final MovieService movieService;

    public MovieRatingSystemCLI() {
        this.movieService = new MovieService();
    }

    public void selectOption() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                showMenu();
                System.out.print("Enter your choice: ");
                int choice = getIntInput(scanner);

                switch (choice) {
                    case 1 -> addMovie(scanner);
                    case 2 -> removeMovie(scanner);
                    case 3 -> updateMovie(scanner);
                    case 4 -> viewMovieDetails(scanner);
                    case 5 -> rateMovie(scanner);
                    case 6 -> viewMoviesByGenre(scanner);
                    case 7 -> viewReviewsByRating(scanner);
                    case 8 -> viewTopRatedMovies();
                    case 9 -> {
                        System.out.println("Exiting... Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n====== Movie Rating System ======");
        System.out.println("1. Add Movie");
        System.out.println("2. Remove Movie");
        System.out.println("3. Update Movie");
        System.out.println("4. View Movie Details");
        System.out.println("5. Rate Movie");
        System.out.println("6. View Movies by Genre");
        System.out.println("7. View Reviews by Rating");
        System.out.println("8. View Top-Rated Movies");
        System.out.println("9. Exit\n");
    }

    private int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private String getValidatedTitle(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(".*[a-zA-Z]+.*")) {
                return input;
            } else {
                System.out.println("Invalid title. Movie title must contain at least one letter.");
            }
        }
    }

    private String getValidatedReview(Scanner scanner) {
        System.out.print("Enter review (press Enter to skip): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty() || input.matches(".*[a-zA-Z]+.*")) {
            return input;
        } else {
            System.out.println("Review must contain at least one letter if provided.");
            return getValidatedReview(scanner); // recursive retry
        }
    }

    private String selectGenre(Scanner scanner) {
        System.out.println("Select Genre:");
        System.out.println("1. Horror");
        System.out.println("2. Action");
        System.out.println("3. Romantic");
        System.out.println("4. Comedy");
        System.out.println("5. Science Fiction");

        while (true) {
            System.out.print("Enter your choice (1-5): ");
            int choice = getIntInput(scanner);
            switch (choice) {
                case 1 -> { return "Horror"; }
                case 2 -> { return "Action"; }
                case 3 -> { return "Romantic"; }
                case 4 -> { return "Comedy"; }
                case 5 -> { return "Science Fiction"; }
                default -> System.out.println("Invalid choice. Please select between 1 and 5.");
            }
        }
    }

    private void addMovie(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title: ");
        String genre = selectGenre(scanner);

        if (movieService.addMovie(title, genre)) {
            System.out.println("Movie added successfully.");
        } else {
            System.out.println("Movie already exists.");
        }
    }

    private void removeMovie(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title to remove: ");

        if (movieService.removeMovie(title)) {
            System.out.println("Movie removed successfully.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    private void updateMovie(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title to update: ");
        String newGenre = selectGenre(scanner);

        if (movieService.updateMovie(title, newGenre)) {
            System.out.println("Movie updated.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    private void viewMovieDetails(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title: ");

        Movie movie = movieService.getMovie(title);
        if (movie == null) {
            System.out.println("Movie not found.");
            return;
        }

        System.out.println(movie);
        List<Rating> ratings = movie.getRatings();
        if (ratings.isEmpty()) {
            System.out.println("No ratings yet.");
        } else {
            System.out.println("Ratings:");
            for (Rating r : ratings) {
                System.out.println(" - " + r);
            }
        }
    }

    private void rateMovie(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title to rate: ");

        int stars;
        while (true) {
            System.out.print("Enter rating (1 to 5): ");
            stars = getIntInput(scanner);
            if (stars >= 1 && stars <= 5) break;
            System.out.println("Error ~ Rating must be between 1 to 5!");
        }

        String review = getValidatedReview(scanner);

        if (movieService.addRating(title, stars, review)) {
            System.out.println("Rating added.");
        } else {
            System.out.println("Movie not found.");
        }
    }

    private void viewMoviesByGenre(Scanner scanner) {
        String genre = selectGenre(scanner);

        List<Movie> movies = movieService.getMoviesByGenre(genre);
        if (movies.isEmpty()) {
            System.out.println("No movies found in this genre.");
        } else {
            System.out.println("Movies in " + genre + ":");
            for (Movie movie : movies) {
                System.out.println(" - " + movie);
            }
        }
    }

    private void viewReviewsByRating(Scanner scanner) {
        String title = getValidatedTitle(scanner, "Enter movie title: ");

        int stars;
        while (true) {
            System.out.print("Enter star rating (1 to 5): ");
            stars = getIntInput(scanner);
            if (stars >= 1 && stars <= 5) break;
            System.out.println("Error ~ Rating must be between 1 to 5!");
        }

        List<Rating> reviews = movieService.getReviewsByRating(title, stars);
        if (reviews.isEmpty()) {
            System.out.println("No reviews with " + stars + " stars found.");
        } else {
            System.out.println(stars + "-star reviews:");
            for (Rating r : reviews) {
                System.out.println(" - " + r);
            }
        }
    }

    private void viewTopRatedMovies() {
        List<Movie> topRated = movieService.getTopRatedMovies();
        if (topRated.isEmpty()) {
            System.out.println("No top-rated movies yet (minimum 5 ratings required).");
        } else {
            System.out.println("Top-Rated Movies:");
            for (Movie movie : topRated) {
                System.out.println(" - " + movie);
            }
        }
    }
}
