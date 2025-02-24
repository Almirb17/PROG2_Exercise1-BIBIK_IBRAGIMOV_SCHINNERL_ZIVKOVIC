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

}