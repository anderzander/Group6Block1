package at.ac.fhcampuswien.fhmdb;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void filter_movies_by_search_field_depending_on_titel(){
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
        List<Movie> actual = homeController.setFilteredBySearchField(sampleMovies, "aBc");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(a);
        expected.add(d);

        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

}