package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Divide;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Movie;

@Service
public class MovieCustomRepositoryImpl implements MovieCustomRepository {
	
	private MongoTemplate mongoTemplate;
	
	@Autowired
    public MovieCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	public Optional<List<Movie>> findMovieByActor(String actor) {
		Query query = new Query();
		query.addCriteria(Criteria.where("cast").is(actor));
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

	@Override
	public Optional<List<Movie>> findMovieByDirector(String director) {
		Query query = new Query();
		query.addCriteria(Criteria.where("director").is(director));
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

	
	@Override
	public Optional<List<Movie>> findMovieByGenre(String genre) {
		Query query = new Query();
		query.addCriteria(Criteria.where("genres").is(genre));
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

	@Override
	public Optional<List<Movie>> findMovieByActorAndRating(String actor, float rating) {
		Query query = new Query();
		query.addCriteria(Criteria.where("cast").is(actor)
				.andOperator(Criteria.where("rating").gt(rating)));
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

	@Override
	public void deleteMovieByMovieId(String movieId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movieId").is(movieId));
		//Movie movie = (Movie) mongoTemplate.find(query,Movie.class);
		mongoTemplate.findAndRemove(query, Movie.class);
	}

	@Override
	public Optional<List<Movie>> findMovieByActorAndDirector(String actor, String director) {
		Query query = new Query();
		query.addCriteria(Criteria.where("cast").is(actor)
				.andOperator(Criteria.where("director").is(director)));
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

	@Override
	public void updateRatingForMovie(String movieId, float avg) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movieId").is(movieId));
		Update update = new Update();
		update.set("avgRating", avg);
		mongoTemplate.updateFirst(query, update, Movie.class);
	}

	@Override
	public Movie updateMovies(String movieId, Movie movie) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movieId").is(movieId));
		Movie movies = mongoTemplate.findOne(query,Movie.class);
		movies.setAvgRating(movie.getAvgRating());
		movies.setCast(movie.getCast());
		movies.setDescription(movie.getDescription());
		movies.setDirector(movie.getDirector());
		movies.setGenres(movie.getGenres());
		movies.setReleaseDate(movie.getReleaseDate());
		movies.setTitle(movie.getTitle());
		return mongoTemplate.save(movies);
//		Update update = new Update();
//		update.set("title", movie.getTitle());
//		update.set("releaseDate", movie.getReleaseDate());
//		update.set("cast", movie.getCast());
//		update.set("director", movie.getDirector());
//		update.set("genres", movie.getGenres());
//		update.set("description", movie.getDescription());
//		update.set("avgRating", movie.getAvgRating());
//		return mongoTemplate.findAndModify(query, update, Movie.class);
		
	}

	@Override
	public Optional<List<Movie>> findSimilarMovies(List<String> genre, String director, List<String> actor) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("director").is(director)
//				.orOperator(Criteria.where("genres").in(genre),Criteria.where("cast").in(actor)));
		
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("genres").in(genre), Criteria.where("cast").in(actor),Criteria.where("director").is(director));
		Query query = new Query(criteria);
		
		List<Movie> movies = mongoTemplate.find(query,Movie.class);
		return Optional.ofNullable(movies);
	}

//	@Override
//	public void updateRatingByMovieId(String movieId, float rating) {
//		 Query query = new Query();
//		 query.addCriteria(Criteria.where("movieId").is(movieId)).fields().include("rating");
//		 //query.fields().include("rating");
//		// float fetchedRating = mongoTemplate.findOne(query, Movie.class).getRating();
//		 float updatedrating =(((mongoTemplate.findOne(query, Movie.class).getRating())+rating)/2);
//		 
//		 Update update = new Update().set("rating",updatedrating);
//		 mongoTemplate.findAndModify(query, update, Movie.class);
//
//
//	}

}
