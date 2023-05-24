package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exceptions.CustomException;

import com.example.demo.Model.Movie;
import com.example.demo.Service.MovieService;

@RestController
@RequestMapping("/MovieApplication/v1/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("/create")
	public Movie createMovie(@RequestBody Movie movie) {
		Movie createdMovie = movieService.saveMovies(movie);
		return createdMovie;
	}
	
	@PostMapping("/createAll")
	public List<Movie> createMovies(@RequestBody List<Movie> movie) {
		List<Movie> createdMovie = movieService.saveAllMovies(movie);
		return createdMovie;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Movie>> getMovies() {
		return new ResponseEntity<List<Movie>>(movieService.findAllMovies(), HttpStatus.OK);
	}

	@GetMapping("/getByImdbId/{movieId}")
	public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String movieId){
		return new ResponseEntity<Optional<Movie>>(movieService.findMovieByMovieId(movieId), HttpStatus.OK);
	}
	
	@GetMapping("/getByActor/{actor}")
	public ResponseEntity<Optional<List<Movie>>> getMoviesByActor(@PathVariable String actor){
		return new ResponseEntity<Optional<List<Movie>>>(movieService.findMovieByActor(actor), HttpStatus.OK);
	}
	
	@GetMapping("/getByDirector/{director}")
	public ResponseEntity<Optional<List<Movie>>> getMoviesByDirector(@PathVariable String director){
		return new ResponseEntity<Optional<List<Movie>>>(movieService.findMovieByDirector(director), HttpStatus.OK);
	}
	
	@GetMapping("/getByGenre/{genre}")
	public ResponseEntity<Optional<List<Movie>>> getMoviesByGenre(@PathVariable String genre){
		return new ResponseEntity<Optional<List<Movie>>>(movieService.findMovieByGenre(genre), HttpStatus.OK);
	}
	
	@GetMapping("/getByActorAndRating/{actor}/{rating}")
	public ResponseEntity<Optional<List<Movie>>> getMoviesByActorAndRating(@PathVariable String actor, @PathVariable float rating){
		return new ResponseEntity<Optional<List<Movie>>>(movieService.findMovieByActorAndRating(actor,rating), HttpStatus.OK);
	}
	
	@GetMapping("/getByActorAndDirector/{actor}/{director}")
	public ResponseEntity<Optional<List<Movie>>> getMoviesByActorAndDirector(@PathVariable String actor, @PathVariable String director){
		return new ResponseEntity<Optional<List<Movie>>>(movieService.findMovieByActorAndDirector(actor,director), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<Optional<Movie>> deleteSingleMovie(@PathVariable ObjectId id){
		movieService.deleteMovie(id);
		return new ResponseEntity<Optional<Movie>>(HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteByMovieId/{movieId}")
	public ResponseEntity<Optional<Movie>> deleteSingleMovieByMovieId(@PathVariable String movieId){
		movieService.deleteMovieByMovieId(movieId);
		return new ResponseEntity<Optional<Movie>>(HttpStatus.OK);
	}
	
	@PutMapping("/updateMovie/{movieId}")
	public Movie updateMovie(@PathVariable String movieId, @RequestBody Movie movie){
		Movie updatedMovie = movieService.updateMovies(movieId, movie);
		return updatedMovie;
	}
	
	@GetMapping("/getSimilarMovies/{movieId}")
	public ResponseEntity<Optional<List<Movie>>> getSimilarMovies(@PathVariable String movieId){
		Optional<Movie> fetchedMovie = movieService.findMovieByMovieId(movieId);
		Optional<List<Movie>> similarMovies = movieService.findSimilarMovies(fetchedMovie.get().getGenres(), fetchedMovie.get().getDirector(), fetchedMovie.get().getCast());
		return new ResponseEntity<Optional<List<Movie>>>(similarMovies, HttpStatus.OK);
	}
//	@PutMapping("/updateRating/{movieId}/{rating}")
//	public ResponseEntity getMoviesAfterUpdatingRating(@PathVariable String movieId, @PathVariable float rating){
//		movieService.updateRatingByMovieId(movieId,rating);
//		return new ResponseEntity("Rating Updated Successfully",HttpStatus.OK);
//	}

}
