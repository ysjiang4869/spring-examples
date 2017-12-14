package org.jys.learn.spring.jpa.springdata.controller;

import io.swagger.annotations.Api;
import org.jys.learn.spring.jpa.springdata.service.AppService;
import org.jys.learn.spring.jpa.springdata.service.article.Article;
import org.jys.learn.spring.jpa.springdata.service.article.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
@Api(tags = "Tag Service")
@RestController
@RequestMapping(value = "/rest/tag")
public class TagController {

    private final AppService svc;

    @Autowired
    public TagController(AppService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Page<Tag> find(@PageableDefault(value = 15, sort = { "description" }, direction = Sort.Direction.DESC) Pageable pageable){
        return svc.getTagService().findAll(pageable);
    }

    @PostMapping
    public Tag add(@RequestBody Tag tag){
        return svc.getTagService().save(tag);
    }

    @GetMapping(value = "/{id}")
    public Tag get(@PathVariable(value = "id")Integer id){
        return svc.getTagService().findOne(id);
    }

    @PutMapping(value = "/{id}")
    public Tag set(@RequestBody Tag tag){
        return svc.getTagService().save(tag);
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Integer id){
        List<Article> articles=svc.getArticleService().findByTags_Id(id);
        articles.forEach(article -> article.getTags().removeIf(tag -> Objects.equals(tag.getId(),id)));
        svc.getArticleService().save(articles);
        svc.getTagService().delete(id);
    }

    @GetMapping(value = "/{id}/article")
    public Page<Article> getArticle(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable,
                                    @PathVariable(value = "id") Integer id){
        return svc.getArticleService().findByTags_Id(pageable,id);
    }

}
