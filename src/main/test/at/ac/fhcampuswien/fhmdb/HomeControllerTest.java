package at.ac.fhcampuswien.fhmdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class HomeControllerTest {
    private static HomeController homeController;

    @BeforeAll
    static void init() {
        homeController = new HomeController();
    }

    //-------------------------------------public void initializeObserverable()-----------------------------------------------------------------------------------
    @Test
    void dummy_films_are_inside_observable_list() {
        homeController.initializeObserverable();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    //-------------------------------------public List<Movie> filterByString(String inputText, List<Movie> movies)------------------------------------------------
    @Test
    void filter_by_string_throws_IllegalArgumentException_when_movie_is_null() {

        assertThrows(IllegalArgumentException.class, () -> {
            homeController.filterByString("Joker", null);
        });
    }

    @Test
    void filter_by_string_do_not_change_observable_list_when_input_text_is_null() {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString(null,homeController.observableMovies);

        //when
        List<Movie> moviesExpected = homeController.observableMovies;
        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void filter_by_string_throws_IllegalArgumentException_when_input_text_and_movie_is_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            homeController.filterByString(null, null);
        });
    }

    @Test
    void filter_by_string_observable_list_unchanged_if_input_empty()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString(" ",homeController.observableMovies);

        //when
        List<Movie> moviesExpected = homeController.observableMovies;
        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void filter_by_string_observable_list_is_empty_if_observable_list_is_empty_from_begin()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> mvs = new ArrayList<>();
        List<Movie> movies = homeController.filterByString("Joker",mvs);

        //when & then
        assertEquals(0,movies.size());
    }

    @Test
    void filter_by_string_observable_list_is_empty_if_observable_list_and_search_string_are_empty_from_begin()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> mvs = new ArrayList<>();
        List<Movie> movies = homeController.filterByString(" ",mvs);

        assertEquals(0,movies.size());
    }


    @ParameterizedTest
    @ValueSource(strings = {"Joker", "joker", "JoKer","joKER", "JOKER"})
    void filter_by_title_string_matches_with_expected_output(String input)
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString(input, homeController.observableMovies);

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
    void filter_by_title_string_matches_with_expected_output_when_search_string_contains_space()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString("jok     er",homeController.observableMovies);

        //when & then
        assertEquals(0,movies.size());
    }


    @Test
    void filter_by_descriptive_string_matches_with_expected_output()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString("wreaks havoc on Gotham",homeController.observableMovies);

        //when
        List<Movie> moviesExpected = Arrays.asList(
                new Movie(
                        "The Dark Knight",
                        "Batman faces his greatest challenge when the Joker wreaks havoc on Gotham, testing the hero's limits and morality.",
                        Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA))
        );

        //then
        assertEquals(moviesExpected,movies);
    }

    @Test
    void filter_by_combined_title_and_description_returns_empty_list()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString("Inception A thief who enters", homeController.observableMovies);

        //when
        List<Movie> moviesExpected = Arrays.asList();

        //then
        assertEquals(moviesExpected, movies);
    }


    //-------------------------------------public List<Movie> filterByGenre(Genre genre, List<Movie> movies)------------------------------------------------------
    @Test
    void filter_by_genre_throws_IllegalArgumentException_when_movie_is_null() {

        assertThrows(IllegalArgumentException.class, () -> {
            homeController.filterByGenre(Genre.DRAMA, null);
        });
    }

    @Test
    void filter_by_genre_do_not_change_observable_list_when_input_genre_is_null() {

        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByGenre(null,homeController.observableMovies);

        //when
        List<Movie> moviesExpected = homeController.observableMovies;
        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void filter_by_genre_throws_IllegalArgumentException_when_movie_and_genre_null() {

        assertThrows(IllegalArgumentException.class, () -> {
            homeController.filterByGenre(null, null);
        });
    }

    @Test
    void filter_by_genre_observable_list_is_empty_if_observable_list_is_empty_from_begin()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> mvs = new ArrayList<>();
        List<Movie> movies = homeController.filterByGenre(Genre.DRAMA,mvs);

        //when & then
        assertEquals(0,movies.size());
    }

    @ParameterizedTest
    @CsvSource({
            "ACTION, 7",
            "ADVENTURE, 5",
            "ANIMATION, 1",
            "BIOGRAPHY, 0",
            "COMEDY, 0",
            "CRIME, 2",
            "DRAMA, 3",
            "DOCUMENTARY, 0",
            "FAMILY, 0",
            "FANTASY, 0",
            "HISTORY, 1",
            "HORROR, 1",
            "MUSICAL, 0",
            "MYSTERY, 1",
            "ROMANCE, 1",
            "SCIENCE_FICTION, 4",
            "SPORT, 0",
            "THRILLER, 5",
            "WAR, 0",
            "WESTERN, 0"
    })
    void filterByGenreWithVariousInputs(Genre genre, int expectedSize) {
        List<Movie> result = homeController.filterByGenre(genre, homeController.allMovies);
        assertEquals(expectedSize, result.size());
    }



    //-------------------------------------filterByGenre && filterByString------------------------------------------------------

    @Test
    void right_search_string_and_right_genre_results_with_expected_output() {
        //given
        homeController.initializeObserverable();
        List<Movie> moviesFilteredByGenre = homeController.filterByGenre(Genre.THRILLER, homeController.observableMovies);
        List<Movie> movies = homeController.filterByString("Skyfall", moviesFilteredByGenre);

        //when
        List<Movie> moviesExpected = new ArrayList<>();
        moviesExpected.add(new Movie(
                "Skyfall",
                "James Bond must uncover the truth behind a cyber attack on MI6 while dealing with a villain from M's past.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.THRILLER)));

        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void right_search_string_but_wrong_genre_returns_empty_list() {
        //given
        homeController.initializeObserverable();
        List<Movie> moviesFilteredByGenre = homeController.filterByGenre(Genre.HISTORY, homeController.observableMovies);
        List<Movie> movies = homeController.filterByString("Skyfall", moviesFilteredByGenre);

        //when
        List<Movie> moviesExpected = Arrays.asList();

        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void wrong_search_string_but_right_genre_returns_empty_list() {
        //given
        homeController.initializeObserverable();
        List<Movie> moviesFilteredByGenre = homeController.filterByGenre(Genre.ACTION, homeController.observableMovies);
        List<Movie> movies = homeController.filterByString("SpiderMan", moviesFilteredByGenre);

        //when
        List<Movie> moviesExpected = Arrays.asList();

        //then
        assertEquals(moviesExpected, movies);
    }

    @Test
    void wrong_search_string_and_wrong_genre_returns_empty_list() {
        //given
        homeController.initializeObserverable();
        List<Movie> moviesFilteredByGenre = homeController.filterByGenre(Genre.ANIMATION, homeController.observableMovies);
        List<Movie> movies = homeController.filterByString("Inceptionn", moviesFilteredByGenre);

        //when
        List<Movie> moviesExpected = Arrays.asList();

        //then
        assertEquals(moviesExpected, movies);
    }

    //-------------------------------------Sorting------------------------------------------------------

    @Test
    void observable_list_is_descending() {
        //given
        homeController.initializeObserverable();
        ObservableList<Movie> oldMoviesOrder = FXCollections.observableArrayList(homeController.observableMovies);

        //when
        homeController.sortObserverable(false);

        ObservableList<Movie> expectedMoviesOrder = FXCollections.observableArrayList(oldMoviesOrder);
        expectedMoviesOrder.sort(Comparator.comparing(Movie::getTitle).reversed());

        //then
        assertEquals(expectedMoviesOrder, homeController.observableMovies);
    }

    @Test
    void observable_list_is_ascending() {
        //given
        homeController.initializeObserverable();
        ObservableList<Movie> oldMoviesOrder = FXCollections.observableArrayList(homeController.observableMovies);

        //when
        homeController.sortObserverable(true);

        ObservableList<Movie> expectedMoviesOrder = FXCollections.observableArrayList(oldMoviesOrder);
        expectedMoviesOrder.sort(Comparator.comparing(Movie::getTitle));

        //then
        assertEquals(expectedMoviesOrder, homeController.observableMovies);
    }
}




