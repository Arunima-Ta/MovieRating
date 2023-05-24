package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Movie;

public interface MovieCustomRepository {

	Optional<List<Movie>> findMovieByActor(String actor);
	
	Optional<List<Movie>> findMovieByGenre(String genre);

	Optional<List<Movie>> findMovieByActorAndRating(String actor, float rating);
	
	void deleteMovieByMovieId(String movieId);
	
	Optional<List<Movie>> findMovieByActorAndDirector(String actor, String director);

	Optional<List<Movie>> findMovieByDirector(String director);

	void updateRatingForMovie(String movieId, float avg);
	
	Movie updateMovies(String movieId, Movie movie);
	
	Optional<List<Movie>> findSimilarMovies(List<String> genre, String director, List<String> actor);
	//void updateRatingByMovieId(String movieId, float rating);
}
