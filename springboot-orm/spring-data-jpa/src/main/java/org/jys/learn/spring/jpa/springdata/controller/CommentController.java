package org.jys.learn.spring.jpa.springdata.controller;


import io.swagger.annotations.Api;
import org.jys.learn.spring.jpa.springdata.service.AppService;
import org.jys.learn.spring.jpa.springdata.service.article.Article;
import org.jys.learn.spring.jpa.springdata.service.article.comment.Comment;
import org.jys.learn.spring.jpa.springdata.service.article.comment.ArticleWithTitleAndId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
@Api(tags = "Comment Service")
@RestController
@RequestMapping(value = "/rest/comment")
public class CommentController {

    private final AppService svc;

    @Autowired
    public CommentController(AppService svc) {
        this.svc = svc;
    }

    /**
     * view all comments only supply for backend service
     * @param pageable page request ?page=n&size=m&sort=f1,desc&sort=f2
     * @return comments
     */
    @GetMapping
    public Page<Comment> find(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Comment> comments=svc.getCommentService().findAllByParent_Id(pageable,null);
        comments.getContent().forEach(comment -> comment.setArticle(svc.getArticleService().findByComments_Id(comment.getId())));
        return comments;
    }

    @GetMapping(value = "/unchecked")
    public Page<Comment> findUnChecked(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Comment> comments=svc.getCommentService().findAllByObjectstate(pageable,0);
        comments.getContent().forEach(comment -> comment.setArticle(svc.getArticleService().findByComments_Id(comment.getId())));
        return comments;
    }

    @GetMapping(value = "/{id}")
    public Comment get(@PathVariable(value = "id")Integer id){
       return svc.getCommentService().findOne(id);
    }

    //TODO all set method need to keep some origin attribute
    //only admin can call this method
    @PutMapping(value = "/{id}")
    public Comment set(@RequestBody Comment comment){
        return svc.getCommentService().save(comment);
    }

    @GetMapping(value = "/{id}/children")
    public Page<Comment> getChildren(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable,
                                     @PathVariable(value = "id")Integer id){
        return svc.getCommentService().findAllByParent_Id(pageable,id);
    }

    @GetMapping(value = "/{id}/parent")
    public Comment getParent(@PathVariable(value = "id")Integer id){
        return svc.getCommentService().findOne(id).getParent();
    }

    @PostMapping(value = "/{id}/children")
    public Comment addChildren(@RequestBody Comment comment,
                               @PathVariable(value = "id")Integer id){
        Comment parent=svc.getCommentService().findOne(id);
        parent.getChildren().add(comment);
        comment.setParent(parent);
        return svc.getCommentService().save(comment);
    }

    @DeleteMapping(value = "/(id)")
    public void delete(@PathVariable(value = "id")Integer id){
        ArticleWithTitleAndId article=svc.getArticleService().findByComments_Id(id);
        Article article1=svc.getArticleService().findOne(article.getId());
        article1.getComments().removeIf(comment->Objects.equals(comment.getId(),id));
        svc.getArticleService().save(article1);
        deleteComment(id);
    }

    private void deleteComment(Integer id){
        Comment comment=svc.getCommentService().findOne(id);
        if(comment.getChildren().size()>0){
            comment.getChildren().forEach(c->deleteComment(c.getId()));
        }
        svc.getCommentService().delete(id);
    }

}
