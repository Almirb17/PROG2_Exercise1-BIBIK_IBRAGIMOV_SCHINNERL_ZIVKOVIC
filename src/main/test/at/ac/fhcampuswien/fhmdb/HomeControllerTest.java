package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;

    @BeforeAll
    static void init() {
        homeController = new HomeController();
    }

    @Test
    void filter_by_genre_with_null_returns_full_list()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByGenre(null,homeController.observableMovies);

        //when
        List<Movie> moviesExpected = homeController.observableMovies;
        //then
        assertEquals(moviesExpected,movies);
    }

    @Test
    void filter_by_string_matches_with_actual_output()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString("Joker",homeController.observableMovies);

        //when
        List<Movie> moviesExpected = Arrays.asList(
                new Movie(
                        "Joker",
                        "A mentally troubled comedian, Arthur Fleck, embarks on a downward spiral of revolution and bloody crime in Gotham City, leading to his alter ego: the Joker.",
                        Arrays.asList(Genre.DRAMA, Genre.THRILLER, Genre.CRIME)),
                new Movie(
                        "The Dark Knight",
                        "Batman faces his greatest challenge when the Joker wreaks havoc on Gotham, testing the hero's limits and morality.",
                        Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA))
        );

        //then
        assertEquals(moviesExpected,movies);
    }

    @Test
    void filter_by_genre_matches_with_actual_output()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByGenre(Genre.ROMANCE,homeController.observableMovies);

        //when

        List<Movie> moviesExpected = new ArrayList<>();
        moviesExpected.add(new Movie(
                "Titanic",
                "A young aristocrat falls in love with a kind but poor artist aboard the ill-fated Titanic, as the ship meets its tragic destiny.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.HISTORY)));
        //then
        assertEquals(moviesExpected,movies);
    }

    @Test
    void at_initialization() {
        homeController.initializeObserverable();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

}