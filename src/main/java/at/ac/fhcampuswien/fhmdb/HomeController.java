package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    protected final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeObserverable();

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("ALL GENRES");
        genreComboBox.getItems().addAll(Genre.values());


        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                observableMovies.sort(Comparator.comparing(Movie::getTitle));
                sortBtn.setText("Sort (desc)");
            } else {
                observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
                sortBtn.setText("Sort (asc)");
            }
        });

        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
            {
                searchBtnClicked(null);
            }
        });


    }

    public void initializeObserverable()
    {
        observableMovies.clear();
        observableMovies.addAll(allMovies);         // add dummy data to observable list
    }

    public void searchBtnClicked(ActionEvent actionEvent) {
        String searchText = searchField.getText().trim().toLowerCase();
        Object selectedGenre = genreComboBox.getSelectionModel().getSelectedItem();

        List<Movie> filteredMovies = allMovies;

        if (!searchText.equals("")) {
            filteredMovies = filterByString(searchText, filteredMovies);
        }
        if (selectedGenre != null && !selectedGenre.toString().equals("ALL GENRES")) {
            filteredMovies = filterByGenre((Genre) selectedGenre, filteredMovies);
        }
        observableMovies.setAll(filteredMovies);


    }

    public List<Movie> filterByString(String inputText, List<Movie> movies) {
        inputText = inputText.toLowerCase();
        List<Movie> result = new ArrayList<>();

        if (inputText == null || inputText.isEmpty()) {
            return movies;
        }

        for (Movie movie : movies) {
            if (movie == null) {
                continue;
            }

            String title = movie.getTitle().toLowerCase();
            String description = movie.getDescription().toLowerCase();

            if (title.contains(inputText) || description.contains(inputText)) {
                result.add(movie);
            }
        }
        return result;

    }

    public List<Movie> filterByGenre(Genre genre, List<Movie> movies) {
        List<Movie> result = new ArrayList<>();
        if (genre == null) {
            return movies;
        }

        for (Movie movie : movies) {
            if (movie == null || movie.getGenres() == null) {
                continue;
            }

            if (movie.getGenres().contains(genre)) {
                result.add(movie);
            }
        }
        return result;
    }
}