package org.jys.server.site.controller;

import io.swagger.annotations.Api;
import org.jys.server.site.service.article.Article;
import org.jys.server.site.service.article.ArticleRepository;
import org.jys.server.site.service.article.review.Review;
import org.jys.server.site.service.article.review.ReviewRepository;
import org.jys.server.site.service.article.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by ysjiang on 2017/12/7.
 *
 */
@Api(tags = "Article Service")
@RestController
@Transactional
@RequestMapping(value = "/rest/article")
public class ArticleController {

    private final ArticleRepository service;
    private final TagRepository tagRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ArticleController(ArticleRepository service, TagRepository tagRepository, ReviewRepository reviewRepository) {
        this.service = service;
        this.tagRepository = tagRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public Page<Article> find(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable){
        return service.findAll(pageable);
    }

    @PostMapping
    public Article add(@RequestBody Article article){
        LocalDateTime now=LocalDateTime.now();
        article.setCrtdate(now);
        article.setUpddate(now);
        return service.save(article);
    }

    @GetMapping(value = "/{id}")
    public Article get(@PathVariable(value = "id")UUID id){
        return service.findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Article set(@RequestBody Article article){
        article.setUpddate(LocalDateTime.now());
        return service.save(article);
    }

    @PostMapping(value = "/{id}/review")
    public void add(@RequestBody Review review,@PathVariable(value = "id")UUID id){
        Review review1=reviewRepository.save(review);
        Article article=service.findOne(id);
        article.getReviews().add(review1);
        service.save(article);
    }
}
