package gr.unipi.movieapi.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.unipi.movieapi.exception.MovieAPIException;
import gr.unipi.movieapi.model.MovieInfo;
import gr.unipi.movieapi.model.themoviedb.ErrorResponse;
import gr.unipi.movieapi.model.themoviedb.MovieResult;
import gr.unipi.movieapi.model.themoviedb.Result;

public class MovieAPIService {
	
	private final String API_URL;
	private final String API_KEY;
	
	
	public MovieAPIService(String aPI_URL, String aPI_KEY) {
		API_URL = aPI_URL;
		API_KEY = aPI_KEY;
	}
	
	
	// get popular movies 
	/* https://api.themoviedb.org/3/discover/movie */
	
	public List<MovieInfo> getPopularMovies() throws MovieAPIException {
		MovieResult result = getAPIData("discover", null, API_URL, API_KEY);
		List<MovieInfo> movieInfoList = new ArrayList<>(result.getResults().size());
		for (Result theResult : result.getResults()) {
			movieInfoList.add(new MovieInfo(theResult));
		}
		return movieInfoList;
	}

	/* https://api.themoviedb.org/3/discover/movie?primary_release_year={year} */
	
	// get popular movies by year
	public List<MovieInfo> getPopularMoviesForYear(int year) throws MovieAPIException {
		MovieResult result = getAPIData("discover", Integer.toString(year), API_URL, API_KEY);
		List<MovieInfo> movieInfoList = new ArrayList<>(result.getResults().size());
		for (Result theResult : result.getResults()) {
			movieInfoList.add(new MovieInfo(theResult));
		}
		return movieInfoList;
	}
	
	// https://api.themoviedb.org/3/search/movie?query={parameter}
	// search for movies
	
	public List<MovieInfo> searchForMovies(String parameter) throws MovieAPIException {
		MovieResult result = getAPIData("search", parameter, API_URL, API_KEY);
		List<MovieInfo> movieInfoList = new ArrayList<>(result.getResults().size());
		for (Result theResult : result.getResults()) {
			movieInfoList.add(new MovieInfo(theResult));
		}
		
		return movieInfoList;
		
	}
	
	
	// get API Data
	
	private MovieResult getAPIData(String apiFunction, String parameter, String API_URL, String API_KEY)
			throws MovieAPIException {
		try {
			
			// https://api.themoviedb.org/3/discover/movie?primary_release_year=2020
			
			
			final URIBuilder uriBuilder = new URIBuilder(API_URL)
					.setPathSegments("3", apiFunction, "movie")
					.addParameter("api_key", API_KEY);
			if (parameter != null && !parameter.isBlank()) {
				switch (apiFunction) {
				case "search":
					uriBuilder.addParameter("query", parameter);
					break;
				case "discover":
					uriBuilder.addParameter("primary_release_year", parameter);
					break;
				}
			}
			final URI uri = uriBuilder.build();

			final HttpGet getRequest = new HttpGet(uri);
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
				final HttpEntity entity = response.getEntity();
				final ObjectMapper mapper = new ObjectMapper();

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
					if (errorResponse.getStatusMessage() != null)
						throw new MovieAPIException("Error occurred on API call: " + errorResponse.getStatusMessage());
				}

				return mapper.readValue(entity.getContent(), MovieResult.class);
			} catch (IOException e) {
				throw new MovieAPIException("Error requesting data from the TheMovieDB API.", e);
			}
		} catch (URISyntaxException e) {
			throw new MovieAPIException("Unable to create request URI.", e);
		}
	}
	
	

}
