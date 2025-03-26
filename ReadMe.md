# 🎥 Movie Rating System (CLI - Java)

This is a command-line based **Movie Rating System** built in Java as part of the Root Code internship technical assessment. It allows users to manage a collection of movies, submit ratings, write reviews, and view top-rated movies categorized by genre.

---

## 📌 Features

- Add, update, and remove movies
- Rate movies (1–5 stars) with optional reviews
- View all ratings for a movie
- Filter movies by genre
- View top-rated movies (at least 5 ratings required)
- View reviews by star rating

---

## 🧱 Technologies Used

- Java 17 (or Java 8+ compatible)
- Object-Oriented Programming (OOP)
- CLI with `Scanner`
- In-memory storage

---

## 🚀 How to Run the Project

### 1. Clone or Download

```bash
git clone https://github.com/lakshanFernando2003/Movie_Rateing_System
cd movie-rating-system
```

> OR unzip the folder if submitted as a `.zip`

---

### 2. Compile and Run

#### 🔗 If you're using IntelliJ or Eclipse:
- Open the project as a Java project.
- Run the `Main` class inside `com.rootcode.movierating`.



## 📸 Sample Interaction

```
====== Movie Rating System ======
1. Add Movie
2. Remove Movie
3. Update Movie
4. View Movie Details
5. Rate Movie
6. View Movies by Genre
7. View Reviews by Rating
8. View Top-Rated Movies
9. Exit

Enter your choice: 1
Enter movie title: Interstellar
Select Genre:
1. Horror
2. Action
3. Romantic
4. Comedy
5. Science Fiction
Enter your choice (1-5): 5
Movie added successfully.
```

---

## ✅ Requirements

- Any IDE (IntelliJ, Eclipse, VS Code) or terminal



## 📂 Project Structure

```
src/
└── com.rootcode.movierating
    ├── cli/
    │   └── MovieRatingSystemCLI.java
    ├── model/
    │   ├── Movie.java
    │   └── Rating.java
    ├── service/
    │   └── MovieService.java
    ├── storage/
    │   └── MovieStore.java
    └── Main.java
```

---

## 🙏 Acknowledgement

Built as part of the technical practical for **Root Code Internship Assessment** by Lakshan fernando.

