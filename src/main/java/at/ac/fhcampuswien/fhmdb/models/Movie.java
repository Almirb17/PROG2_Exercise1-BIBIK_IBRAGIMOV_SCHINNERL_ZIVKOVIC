package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(
                "Joker",
                "A mentally troubled comedian, Arthur Fleck, embarks on a downward spiral of revolution and bloody crime in Gotham City, leading to his alter ego: the Joker.",
                Arrays.asList(Genre.DRAMA, Genre.THRILLER, Genre.CRIME)));

        movies.add(new Movie(
                "Spider-Man",
                "After being bitten by a genetically altered spider, Peter Parker gains superpowers and must protect New York City from the villainous Green Goblin.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));

        movies.add(new Movie(
                "Spider-Man: Across the Spider-Verse",
                "Miles Morales embarks on an epic adventure through the multiverse, encountering different versions of Spider-People while facing a powerful new threat.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.ANIMATION, Genre.SCIENCE_FICTION)));

        movies.add(new Movie(
                "Baghead",
                "A mysterious supernatural being grants people the ability to communicate with the dead—at a terrifying cost.",
                Arrays.asList(Genre.HORROR, Genre.MYSTERY, Genre.THRILLER)));

        movies.add(new Movie(
                "Titanic",
                "A young aristocrat falls in love with a kind but poor artist aboard the ill-fated Titanic, as the ship meets its tragic destiny.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.HISTORY)));

        movies.add(new Movie(
                "Inception",
                "A thief who enters people's dreams to steal secrets is given the task of planting an idea into someone's mind.",
                Arrays.asList(Genre.ACTION, Genre.SCIENCE_FICTION, Genre.THRILLER)));

        movies.add(new Movie(
                "The Dark Knight",
                "Batman faces his greatest challenge when the Joker wreaks havoc on Gotham, testing the hero's limits and morality.",
                Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA)));

        movies.add(new Movie(
                "Avatar",
                "A paraplegic Marine is sent to the moon Pandora, where he becomes torn between following orders and protecting its indigenous people.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));

        movies.add(new Movie(
                "Mission: Impossible – Fallout",
                "Ethan Hunt and his team must race against time after a mission goes wrong, leading to a deadly nuclear threat.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.THRILLER)));

        movies.add(new Movie(
                "Skyfall",
                "James Bond must uncover the truth behind a cyber attack on MI6 while dealing with a villain from M's past.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.THRILLER)));

        return movies;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie movie = (Movie) obj;
        return title.equals(movie.title) &&
                description.equals(movie.description) &&
                genres.equals(movie.genres);
    }
}
