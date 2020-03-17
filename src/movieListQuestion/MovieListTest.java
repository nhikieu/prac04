package movieListQuestion;

import static org.junit.jupiter.api.Assertions.*;

import movieListAnswer.MovieList;
import org.junit.jupiter.api.*;

/* The following tests are for the basic functionality of
 * the movie list class
 *
 * You should uncomment them one at a time and follow the
 * "red, green, refactor" strategy to get each one working
 *
 * NB: You will not be able to run this file as a JUnit test
 * until you have uncommented Test 1, because IntelliJ won't
 * recognise MovieListTest as a JUnit "runner" until it
 * contains at least one "@Test" method
 */


public class MovieListTest {

	/* Test 0: Declaring MovieList objects
	 * [This test obliges you to create a class called "MovieList".]
	 */
MovieList movies;


	/* Test 1: Constructing a MovieList object
	 */
	@BeforeEach @Test
	public void setUpMovieList() {
		movies = new MovieList();
	}

	/* Test 2: Adding a new movie to the list
	 */
	@Test
	public void addAMovie() throws MovieListException {
		movies.addMovie("Star Wars");
		assertEquals("No rating", movies.getRating("Star Wars"),
				"Adding movie failed");
	}

	/* Test 3: Associating a rating with a movie
	 */
	@Test
	public void setARating() throws MovieListException {
		movies.addMovie("Goldfinger");
		movies.setRating("Goldfinger", 4);
		assertEquals("****", movies.getRating("Goldfinger"),
				"Couldn't set a rating");
	}

	/* Test 4: Getting a printable list of movies
	 */
	@Test
	public void listAlphabetically() throws MovieListException {
		String orderedListing =
				"Casablanca\n" +
						"Maltese Bippy, The\n" +
						"Star Wars\n";
		movies.addMovie("Star Wars");
		movies.addMovie("Casablanca");
		movies.addMovie("Maltese Bippy, The");
		assertEquals(orderedListing, movies.getList(), "Listing not alphabetical");
	}

	/* Test 5: Can't get a rating for an unrated movie
	 */
	@Test
	public void nonexistentRating() {
		assertThrows(MovieListException.class, () -> {
			movies.getRating("The Ghost in the Invisible Bikini");
		});
	}

	/* Test 6: Can't add the same movie twice
	 */
	@Test
	public void duplicateMovie() {
		assertThrows(MovieListException.class, () -> {
			movies.addMovie("Earth Versus the Flying Saucers");
			movies.addMovie("Earth Versus the Flying Saucers");
		});
	}

	/* Test 7: Even a bomb gets a single star
	 */
	@Test
	public void ratingTooLow() {
		assertThrows(MovieListException.class, () -> {
			movies.addMovie("Plan Nine From Outer Space");
			movies.setRating("Plan Nine From Outer Space", 0);
		});
	}

	/* Test 8: No movie gets more than five stars
	 */
	@Test
	public void ratingTooHigh() {
		assertThrows(MovieListException.class, () -> {
			movies.addMovie("Citizen Kane");
			movies.setRating("Citizen Kane", 6);
		});
	}

	/* Test 9: Can't rate an unknown movie
	 */
	@Test
	public void ratingUnknownMovie() {
		assertThrows(MovieListException.class, () -> {
			movies.setRating("Them!", 4);
		});
	}

	/* Test 10: Set a rating using thumbs
	 */
	@Test
	public void setThumbsRating() throws MovieListException{
		movies.addMovie("Joker");
		movies.setThumbRating("Joker", 2);
		movies.addMovie("IT");
		movies.setThumbRating("IT", -2);
		assertEquals("Two thumbs up", movies.getRating("Joker"));
		assertEquals("Two thumbs down", movies.getRating("IT"));
		movies.addMovie("The Flu");
		movies.setRating("The Flu", 2);
		assertEquals("**", movies.getRating("The Flu"));
	}
}
