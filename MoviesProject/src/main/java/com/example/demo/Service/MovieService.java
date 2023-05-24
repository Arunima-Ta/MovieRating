package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Movie;
import com.example.demo.Repo.MovieCustomRepository;
import com.example.demo.Repo.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieCustomRepository movieCustomRepository;

	public Movie saveMovies(Movie movie){

		movie.isValid();

		return movieRepository.insert(movie);
	}

	public List<Movie> saveAllMovies(List<Movie> movie) {
		return movieRepository.saveAll(movie);
	}

	public Movie updateMovies(String movieId, Movie movie){

		movie.isValid();

		return movieRepository.updateMovies(movieId, movie);
	}

	public List<Movie> findAllMovies() {
		return movieRepository.findAll();
	}

	public Optional<Movie> findMovieByMovieId(String movieId) {
		return movieRepository.findMovieByMovieId(movieId);
	}

	public Optional<List<Movie>> findMovieByActor(String actor) {
		return movieRepository.findMovieByActor(actor);
	}

	public Optional<List<Movie>> findMovieByDirector(String director) {
		return movieRepository.findMovieByDirector(director);
	}

	public Optional<List<Movie>> findMovieByGenre(String genre) {
		return movieRepository.findMovieByGenre(genre);
	}

	public Optional<List<Movie>> findMovieByActorAndRating(String actor, float rating) {
		return movieRepository.findMovieByActorAndRating(actor,rating);
	}

	public Optional<List<Movie>> findMovieByActorAndDirector(String actor, String director) {
		return movieRepository.findMovieByActorAndDirector(actor, director);
	}

	public void deleteMovie(ObjectId id) {
		movieRepository.deleteById(id);
	}

	public void deleteMovieByMovieId(String movieId) {
		movieRepository.deleteMovieByMovieId(movieId);
	}
	
	public Optional<List<Movie>> findSimilarMovies(List<String> genre, String director, List<String> actor){
		return movieRepository.findSimilarMovies(genre, director, actor);
	}


	//	public void updateRatingByMovieId(String movieId, float rating) {
	//		movieCustomRepository.updateRatingByMovieId(movieId, rating);
	//	}
}
