package org.jys.learn.spring.jpa.springdata.controller;

import io.swagger.annotations.Api;
import org.jys.learn.spring.jpa.springdata.service.AppService;
import org.jys.learn.spring.jpa.springdata.service.SpecUtils;
import org.jys.learn.spring.jpa.springdata.service.article.Article;
import org.jys.learn.spring.jpa.springdata.service.article.comment.Comment;
import org.jys.learn.spring.jpa.springdata.service.article.tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
@Api(tags = "Article Service")
@RestController
@RequestMapping(value = "/rest/article")
public class ArticleController {

    private final AppService svc;

    @Autowired
    public ArticleController(AppService svc) {
        this.svc = svc;
    }

    /**
     * find articles
     * @param pageable page and sort
     * @param objectstate 1-normal view, 0-draft,9-delete
     * @return article list
     */
    @GetMapping
    public Page<Article> find(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable,
                              @RequestParam(value = "objectstate",defaultValue = "1")Short objectstate){
        Map<String,Object> params=new HashMap<>();
        params.put("objectstate",objectstate);
        Page<Article> articles=svc.getArticleService().findAll(SpecUtils.getArticleSpecs(params),pageable);
        articles.forEach(article -> {
            article.setBody(null);
            filterComment(article);
        });
        return articles;
    }

    @PostMapping
    public Article add(@RequestBody Article article){
        LocalDateTime now=LocalDateTime.now();
        article.setCrtdate(now);
        article.setUpddate(now);
        return svc.getArticleService().save(article);
    }

    @GetMapping(value = "/{id}")
    public Article get(@PathVariable(value = "id")UUID id){
        Article article=svc.getArticleService().findOne(id);
        article.getComments().forEach(this::filterComment);
        return article;
    }

    @PutMapping(value = "/{id}")
    public Article set(@RequestBody Article article){
        article.setUpddate(LocalDateTime.now());
        return svc.getArticleService().save(article);
    }

    //TODO: once comment after my check ,auto send a email to the comment creator
    @PostMapping(value = "/{id}/comment")
    public void add(@RequestBody Comment comment, @PathVariable(value = "id")UUID id,
                    HttpServletResponse response) throws IOException {
        if(comment.getParent()!=null||comment.getChildren().size()>0){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"parent/children should be null,replay please use /comment/id/children");
        }
        comment.setCrtdate(LocalDateTime.now());
        Comment savecomment = svc.getCommentService().save(comment);
        Article article=svc.getArticleService().findOne(id);
        article.getComments().add(savecomment);
        svc.getArticleService().save(article);
    }


    @PostMapping(value = "/{id}/tags")
    public void add(@RequestBody List<Integer> tags, @PathVariable(value = "id")UUID id){
        Article article=svc.getArticleService().findOne(id);
        article.setTags(new ArrayList<>());
        tags.forEach(tagId->{
            Tag tag=svc.getTagService().findOne(tagId);
            article.getTags().add(tag);
        });
        svc.getArticleService().save(article);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id")UUID id){
        Article article=svc.getArticleService().findOne(id);
        article.setObjectstate((short)9);
        svc.getArticleService().save(article);
    }

    @DeleteMapping(value = "/{id}/force")
    public void deleteFinal(@PathVariable(value = "id")UUID id){
       svc.getArticleService().delete(id);
    }

    private void filterComment(Article article){
        article.getComments().removeIf(c->Objects.equals(c.getObjectstate(),(short)0));
        article.getComments().forEach(this::filterComment);
    }
    private void filterComment(Comment comment){
        comment.getChildren().removeIf(c->Objects.equals(c.getObjectstate(),(short)0));
        comment.getChildren().forEach(this::filterComment);
    }
}
