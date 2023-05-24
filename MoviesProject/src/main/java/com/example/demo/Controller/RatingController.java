package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Movie;
import com.example.demo.Model.Rating;
import com.example.demo.Service.RatingService;

@RestController
@RequestMapping("/MovieApplication/v1/ratings")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping("/create")
	public Rating createMovie(@RequestBody Rating rating) {

		Rating createdRating = ratingService.saveRatings(rating);
		ratingService.averageRating(rating.getMovieId());
		//call the funtion to update the avgRating in movie database
		return createdRating;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Rating>> getRatings() {
		return new ResponseEntity<List<Rating>>(ratingService.findAllRatings(), HttpStatus.OK);
	}
	
}
