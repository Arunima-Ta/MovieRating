package com.example.demo.Repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Rating;

@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId>, RatingCustomRepository {

	//Optional<Rating> findRatingByMovieId(String movieId);
}
