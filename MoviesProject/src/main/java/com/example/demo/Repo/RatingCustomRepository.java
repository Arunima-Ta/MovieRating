package com.example.demo.Repo;

import java.util.List;
import java.util.Optional;

import com.example.demo.Model.Rating;

public interface RatingCustomRepository {
	
	Optional<List<Rating>> findRatingByMovieId(String movieId);

}
