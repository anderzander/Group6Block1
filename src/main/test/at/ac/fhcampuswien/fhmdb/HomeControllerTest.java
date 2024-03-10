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
        homeController.sortObservableMovies("Sort (asc)");

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
        homeController.sortObservableMovies("Sort (desc)");

        //then
        List<Movie> expected = new ArrayList<>();
        expected.add(zaa);
        expected.add(baa);
        expected.add(aba);
        expected.add(aab);

        assertEquals(expected, homeController.getObservableMovies());

    }
}