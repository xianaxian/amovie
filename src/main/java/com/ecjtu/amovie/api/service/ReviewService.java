package com.ecjtu.amovie.api.service;

import com.ecjtu.amovie.api.entity.Review;
import com.ecjtu.amovie.api.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xianaxian  2019-08-27 20:37
 */
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<Review> getMoviesReview(Integer moviesId){
        return reviewRepository.selectByMovie(moviesId);
    }
    public int saveReview(Review review){
        return reviewRepository.saveOne(review);
    }
}
