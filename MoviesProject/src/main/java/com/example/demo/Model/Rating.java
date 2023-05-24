package com.example.demo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
public class Rating {

	@Id
	private ObjectId id;
	
	private String movieId;
	private String movieName;
	private String userName;
	private float rating;
	
	public Rating(String movieId, String movieName, String userName, float rating) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.userName = userName;
		this.rating = rating;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rating [id=");
		builder.append(id);
		builder.append(", movieId=");
		builder.append(movieId);
		builder.append(", movieName=");
		builder.append(movieName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", rating=");
		builder.append(rating);
		builder.append("]");
		return builder.toString();
	}
	
	
}
