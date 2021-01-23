package gr.unipi.movieapi.model;

import gr.unipi.movieapi.model.themoviedb.Result;

public class MovieInfo {
	
	private final String title;
	private final String description;
	private final String rating;
	private final String release_date;
	
	public MovieInfo(String title, String description, String rating, String release_date) {
		this.title = title;
		this.description = description;
		this.rating = rating;
		this.release_date = release_date;
	}

	public MovieInfo(Result theResult) {
		this.title = theResult.getTitle();
		this.description = theResult.getOverview();
		this.release_date = theResult.getReleaseDate();
		this.rating = theResult.getVoteAverage().toString();
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getRating() {
		return rating;
	}

	public String getRelease_date() {
		return release_date;
	}
	
	@Override
    public String toString() {
        return "MovieInfo{" +
                "title='" + title + "'\n" +
                ", description='" + description + "'\n" +
                ", rating='" + rating + "'\n" +
                ", release_date='" + release_date + "'\n" +
                '}';
    }
	

}
