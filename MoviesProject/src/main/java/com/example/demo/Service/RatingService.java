package com.example.demo.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Movie;
import com.example.demo.Model.Rating;
import com.example.demo.Repo.MovieCustomRepository;
import com.example.demo.Repo.MovieRepository;
import com.example.demo.Repo.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private MovieRepository movieRepository;


	public Rating saveRatings(Rating rating) {
		
		return ratingRepository.insert(rating);
	}

	public void averageRating(String movieId) {
		
		//get allthe movies based on the movie ID
		// avg the rating and push it to the movie database
		
		Optional<List<Rating>> listOfRating = ratingRepository.findRatingByMovieId(movieId);
		int size = listOfRating.get().size();
		ArrayList<Float> ar = new ArrayList<Float>();
		listOfRating.get().stream().forEach(rating->{
			ar.add(rating.getRating());

		});
		double floatsSum = ar.stream()
	            .mapToDouble(Float::doubleValue).sum();
	           
		Float avg = (float) (floatsSum/size);
		if(avg != null) {
			movieRepository.updateRatingForMovie(movieId,avg);
		}
		
	}

	public List<Rating> findAllRatings() {
			return ratingRepository.findAll();
		
	}
	
}
