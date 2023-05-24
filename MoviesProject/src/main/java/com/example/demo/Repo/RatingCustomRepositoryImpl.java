package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Rating;

@Service
public class RatingCustomRepositoryImpl implements RatingCustomRepository {

	private MongoTemplate mongoTemplate;

	@Autowired
	public RatingCustomRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Optional<List<Rating>> findRatingByMovieId(String movieId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("movieId").is(movieId));
		List<Rating> listOfRating = mongoTemplate.find(query,Rating.class);
		return Optional.ofNullable(listOfRating);
	}

}
