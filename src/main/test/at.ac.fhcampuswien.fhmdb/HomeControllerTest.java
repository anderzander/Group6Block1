package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.sort.AscendingSort;
import at.ac.fhcampuswien.fhmdb.sort.DescendingSort;
import at.ac.fhcampuswien.fhmdb.sort.MovieSort;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void filter_movies_by_search_field_depending_on_titel() {
        //given
        HomeController homeController = new HomeController();

        Movie a = new Movie("abc2", "xyz", null);
        Movie b = new Movie("B", "xyz", null);
        Movie c = new Movie("acb", "yxz", null);
        Movie d = new Movie("xyz 7 abCK", "xyz", null);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);

        //when
        List<Movie> actual = homeController.listFilteredBySearchField(sampleMovies, "aBc");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(a);
        expected.add(d);

        assertTrue(actual.containsAll(expected));
        assertEquals(2, actual.size());
    }

    @Test
    void sort_asc_by_titel() {
        //given
        HomeController homeController = new HomeController();

        Movie aab = new Movie("Aab", null, null);
        Movie aba = new Movie("Aba", null, null);
        Movie baa = new Movie("baa", null, null);
        Movie zaa = new Movie("zaa", null, null);

        homeController.allMovies.clear();
        homeController.allMovies.add(aab);
        homeController.allMovies.add(zaa);
        homeController.allMovies.add(aba);
        homeController.allMovies.add(baa);

        Collections.shuffle(homeController.allMovies);
        homeController.getObservableMovies().addAll(homeController.allMovies);

        // when
        homeController.sortObservableMovies();

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(aab);
        expected.add(aba);
        expected.add(baa);
        expected.add(zaa);

        assertEquals(expected, homeController.getObservableMovies());

    }

    @Test
    void sort_desc_by_titel() {
        //given
        HomeController homeController = new HomeController();

        Movie aab = new Movie("Aab", null, null);
        Movie aba = new Movie("Aba", null, null);
        Movie baa = new Movie("baa", null, null);
        Movie zaa = new Movie("zaa", null, null);

        homeController.allMovies.clear();
        homeController.allMovies.add(aab);
        homeController.allMovies.add(zaa);
        homeController.allMovies.add(aba);
        homeController.allMovies.add(baa);

        Collections.shuffle(homeController.allMovies);
        homeController.getObservableMovies().addAll(homeController.allMovies);

        // when
        MovieSort movieSort = new MovieSort();
        movieSort.setState( new DescendingSort());
        movieSort.sort(homeController.getObservableMovies());
        //homeController.sortObservableMovies();

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(zaa);
        expected.add(baa);
        expected.add(aba);
        expected.add(aab);

        assertEquals(expected, homeController.getObservableMovies());


    }


    @Test
    void filter_movie_by_genre1() {
        //given
        HomeController homeController = new HomeController();

        List<Genre> genreListForMovieA = new ArrayList<>();
        genreListForMovieA.add(Genre.ACTION);
        genreListForMovieA.add(Genre.COMEDY);
        Movie movieA = new Movie("A", null, genreListForMovieA);

        List<Genre> genreListForMovieB = new ArrayList<>();
        genreListForMovieB.add(Genre.THRILLER);
        genreListForMovieB.add(Genre.ACTION);
        Movie movieB = new Movie("B", null, genreListForMovieB);

        List<Genre> genreListForMovieC = new ArrayList<>();
        genreListForMovieC.add(Genre.THRILLER);
        genreListForMovieC.add(Genre.COMEDY);
        Movie movieC = new Movie("C", null, genreListForMovieC);

        List<Movie> sampleMovies = new ArrayList<>();

        sampleMovies.add(movieA);
        sampleMovies.add(movieB);
        sampleMovies.add(movieC);


        //when
        List<Movie> actual = homeController.listFilteredByGenres(sampleMovies, "COMEDY");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(movieA);
        expected.add(movieC);

        assertTrue(actual.containsAll(expected));
        assertEquals(2, actual.size());
    }

    @Test
    void filter_movie_by_genre2() {
        //given
        HomeController homeController = new HomeController();

        List<Genre> genreListForMovieA = new ArrayList<>();
        genreListForMovieA.add(Genre.COMEDY);
        Movie movieA = new Movie("A", null, genreListForMovieA);

        List<Genre> genreListForMovieB = new ArrayList<>();
        genreListForMovieB.add(Genre.THRILLER);
        genreListForMovieB.add(Genre.DRAMA);
        Movie movieB = new Movie("B", null, genreListForMovieB);

        List<Genre> genreListForMovieC = new ArrayList<>();
        genreListForMovieC.add(Genre.THRILLER);
        genreListForMovieC.add(Genre.COMEDY);
        genreListForMovieC.add(Genre.DRAMA);
        Movie movieC = new Movie("C", null, genreListForMovieC);

        List<Movie> sampleMovies = new ArrayList<>();

        sampleMovies.add(movieA);
        sampleMovies.add(movieB);
        sampleMovies.add(movieC);


        //when
        List<Movie> actual = homeController.listFilteredByGenres(sampleMovies, "DRAMA");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(movieB);
        expected.add(movieC);


        assertTrue(actual.containsAll(expected));
        assertEquals(2, actual.size());
    }


    @Test
    void filter_movies_by_search_field_depending_on_description() {
        //given
        HomeController homeController = new HomeController();

        Movie a = new Movie("abc2", "movie", null);
        Movie b = new Movie("B", "xyz", null);
        Movie c = new Movie("acb", "yes, this movie also", null);
        Movie d = new Movie("xyz 7 abCK", "JAGEmoVie12", null);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);

        //when
        List<Movie> actual = homeController.listFilteredBySearchField(sampleMovies, "MOVIE");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(a);
        expected.add(c);
        expected.add(d);

        assertTrue(actual.containsAll(expected));
        assertEquals(3, actual.size());
    }

    @Test
    void stream_filter_most_popular_actor() {
        //given
        List<String> mainCastListA = new ArrayList<>();
        mainCastListA.add("Actor1");
        mainCastListA.add("Actor2");
        mainCastListA.add("Actor3");
        List<String> mainCastListB = new ArrayList<>();
        mainCastListB.add("Actor1");
        mainCastListB.add("Actor2");
        List<String> mainCastListC = new ArrayList<>();
        mainCastListC.add("Actor1");
        mainCastListB.add("Actor2");
        List<String> mainCastListD = new ArrayList<>();
        mainCastListD.add("Actor1");

        Movie a = new Movie("", "", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), mainCastListA, 0.0);
        Movie b = new Movie("", "", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), mainCastListB, 0.0);
        Movie c = new Movie("", "", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), mainCastListC, 0.0);
        Movie d = new Movie("", "", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), mainCastListD, 0.0);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);

        //when
        String actual = HomeController.getMostPopularActor(sampleMovies);
        String expected = "Actor1";

        //expected
        assertEquals(expected, actual);
    }
    @Test
    void stream_filter_longest_title_given_number_length(){
        //given


        Movie a = new Movie("", "title1", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie b = new Movie("", "title2", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie c = new Movie("", "titleLongest", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie d = new Movie("", "title3", new ArrayList<>(), 0, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);
        //when
        int actual = HomeController.getLongestMovieTitle(sampleMovies);
        int expected = 12;

        //then
        assertEquals(actual,expected);
    }
    @Test
    void given_numbers_of_Regisseurs() {
        //given
        List<String> directorList1 = new ArrayList<>();
        directorList1.add("director1");
        directorList1.add("director2");
        directorList1.add("director3");
        List<String> directorList2 = new ArrayList<>();
        directorList2.add("director2");
        List<String> directorList3 = new ArrayList<>();
        directorList3.add("director2");
        directorList3.add("director4");
        List<String> directorList4 = new ArrayList<>();
        directorList4.add("director1");
        directorList4.add("director4");

        Movie a = new Movie("", "", new ArrayList<>(), 0, "", "", 0, directorList1, new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie b = new Movie("", "", new ArrayList<>(), 0, "", "", 0, directorList2, new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie c = new Movie("", "", new ArrayList<>(), 0, "", "", 0, directorList3, new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie d = new Movie("", "", new ArrayList<>(), 0, "", "", 0, directorList4, new ArrayList<>(), new ArrayList<>(), 0.0);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);

        //when
                //parametrized tests
        long actual = HomeController.countMoviesFrom(sampleMovies,"director4");
        long expected = 2;

        //then
        assertEquals(expected,actual);
    }
    @Test
    void given_movie_between_2000_and_2002(){
        //given
        Movie a = new Movie("", "", new ArrayList<>(), 2002, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie b = new Movie("", "", new ArrayList<>(), 2009, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie c = new Movie("", "", new ArrayList<>(), 2000, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);
        Movie d = new Movie("", "", new ArrayList<>(), 2001, "", "", 0, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0.0);

        List<Movie> sampleMovies = new ArrayList<>();
        sampleMovies.add(a);
        sampleMovies.add(b);
        sampleMovies.add(c);
        sampleMovies.add(d);

        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(a);
        expectedList.add(c);
        expectedList.add(d);

        //when
        List<Movie> actual = HomeController.getMoviesBetweenYears(sampleMovies,2000,2002);
        List<Movie> expected = expectedList ;

        //then
        assertEquals(actual,expected);

    }
}