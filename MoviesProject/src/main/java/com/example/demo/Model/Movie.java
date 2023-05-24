package com.example.demo.Model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.example.demo.exceptions.CustomException;


@Document(collection = "movies")
public class Movie {

	@Id
	private ObjectId id;

	private String movieId;
	private String title;
	private String releaseDate;
	private List<String> cast;
	private String director;
	private List<String> genres;
	private String description;
	private float avgRating;
	//	@DocumentReference
	//	private List<Rating> ratings;

	public Movie() {
		super();
	}


	public Movie(String movieId, String title, String releaseDate, List<String> cast, String director,
			List<String> genres, String description, float avgRating) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
		this.cast = cast;
		this.director = director;
		this.genres = genres;
		this.description = description;
		this.avgRating = avgRating;
	}


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	//	public List<Rating> getRatings() {
	//		return ratings;
	//	}
	//
	//	public void setRatings(List<Rating> ratings) {
	//		this.ratings = ratings;
	//	}

	public float getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Movie [id=");
		builder.append(id);
		builder.append(", movieId=");
		builder.append(movieId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", releaseDate=");
		builder.append(releaseDate);
		builder.append(", cast=");
		builder.append(cast);
		builder.append(", director=");
		builder.append(director);
		builder.append(", genres=");
		builder.append(genres);
		builder.append(", description=");
		builder.append(description);
		builder.append(", avgRating=");
		builder.append(avgRating);
		builder.append("]");
		return builder.toString();
	}

	public void isValid() throws CustomException {
		if(description.length()>200) {
			throw new CustomException("Description exceeds the limit");
		}
	}

}


