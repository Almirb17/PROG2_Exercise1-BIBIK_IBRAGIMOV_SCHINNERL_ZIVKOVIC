package at.ac.fhcampuswien.fhmdb;

//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
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


    //-------------------------------------public void initialize(URL url, ResourceBundle resourceBundle)---------------------------------------------------------



    //-------------------------------------public void initializeObserverable()-----------------------------------------------------------------------------------
    @Test
    void dummy_films_are_inside_observable_list() {
        homeController.initializeObserverable();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }
    //-------------------------------------public void searchBtnClicked(ActionEvent actionEvent)------------------------------------------------------------------



    //-------------------------------------public List<Movie> filterByString(String inputText, List<Movie> movies)------------------------------------------------
//    @ParameterizedTest
//    @ValueSource(strings = {"Joker", "joker", "JoKer","joKER"})
//    void filter_by_string_matches_with_actual_output(String input)
//    {
//        //given
//        homeController.initializeObserverable();
//        List<Movie> movies = homeController.filterByString(input, homeController.observableMovies);
//
//        //when
//        List<Movie> moviesExpected = Arrays.asList(
//                new Movie(
//                        "Joker",
//                        "A mentally troubled comedian, Arthur Fleck, embarks on a downward spiral of revolution and bloody crime in Gotham City, leading to his alter ego: the Joker.",
//                        Arrays.asList(Genre.DRAMA, Genre.THRILLER, Genre.CRIME)),
//                new Movie(
//                        "The Dark Knight",
//                        "Batman faces his greatest challenge when the Joker wreaks havoc on Gotham, testing the hero's limits and morality.",
//                        Arrays.asList(Genre.ACTION, Genre.CRIME, Genre.DRAMA))
//        );
//
//        //then
//        assertEquals(moviesExpected,movies);
//    }

    @Test
    void filter_by_string_matches_with_expected_output_when_search_string_contains_space()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString("jok er",homeController.observableMovies);

        //when & then
        assertEquals(0,movies.size());
    }

    @Test
    void filter_by_string_when_input_empty_output_nothing_should_happen_to_observable_list()
    {
        //given
        homeController.initializeObserverable();
        List<Movie> movies = homeController.filterByString(" ",homeController.observableMovies);

        //when & then
        assertEquals(10, movies.size());
    }

    //-------------------------------------public List<Movie> filterByGenre(Genre genre, List<Movie> movies)------------------------------------------------------
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
}