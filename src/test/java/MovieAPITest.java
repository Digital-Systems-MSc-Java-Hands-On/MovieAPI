import java.util.List;

import gr.unipi.movieapi.MovieAPI;
import org.junit.Assert;
import org.junit.Test;

import gr.unipi.movieapi.exception.MovieAPIException;
import gr.unipi.movieapi.model.MovieInfo;
import gr.unipi.movieapi.services.MovieAPIService;

public class MovieAPITest {

	@Test
	public void testSearchAPI() throws MovieAPIException {
		final MovieAPIService movieSearchService = MovieAPI.getMovieDBService();
		final List<MovieInfo> results = movieSearchService.searchForMovies("Star Wars");
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}
	
	@Test
    public void testDiscoverAPI() throws MovieAPIException {
		final MovieAPIService movieSearchService = MovieAPI.getMovieDBService();
        final List<MovieInfo> results = movieSearchService.getPopularMovies();
        Assert.assertFalse(results.isEmpty());
        results.forEach(System.out::println);
    }
	
	@Test
    public void testDiscoverAPIwithYear() throws MovieAPIException {
        final MovieAPIService movieSearchService = MovieAPI.getMovieDBService();
        final List<MovieInfo> results = movieSearchService.getPopularMoviesForYear(2020);
        Assert.assertFalse(results.isEmpty());
        results.forEach(System.out::println);
    }

    @Test
    public void testDiscoverAPIInvalidYear() throws MovieAPIException {
        final MovieAPIService movieSearchService = MovieAPI.getMovieDBService();
        final List<MovieInfo> results = movieSearchService.getPopularMoviesForYear(5555);
        Assert.assertTrue(results.isEmpty());
    }

}
