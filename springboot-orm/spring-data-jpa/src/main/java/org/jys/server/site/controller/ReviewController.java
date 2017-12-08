package org.jys.server.site.controller;


import io.swagger.annotations.Api;
import org.jys.server.site.service.article.review.Review;
import org.jys.server.site.service.article.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
@Api(tags = "Review Service")
@RestController
@Transactional
@RequestMapping(value = "/rest/review")
public class ReviewController {

    private final ReviewRepository service;

    @Autowired
    public ReviewController(ReviewRepository service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> find(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review get(@PathVariable(value = "id")Integer id){
        return service.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Review set(@RequestBody Review review){
        return service.save(review);
    }
}
