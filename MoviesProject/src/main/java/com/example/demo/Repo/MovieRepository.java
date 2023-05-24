package com.example.demo.Repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Movie;

@Repository
@Primary
public interface MovieRepository extends MongoRepository<Movie,ObjectId>, MovieCustomRepository {

	Optional<Movie> findMovieByMovieId(String movieId);
		
	
}
