package org.jys.learn.spring.jpa.springdata.controller;

import io.swagger.annotations.Api;
import org.jys.learn.spring.jpa.springdata.service.AppService;
import org.jys.learn.spring.jpa.springdata.service.article.book.Book;
import org.jys.learn.spring.jpa.springdata.service.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ysjiang on 2017/12/13.
 *中文翻译：文集
 * @author Jiang YueSong
 * @date 2017/12/13
 */
@Api(tags = "Book Service")
@RestController
@RequestMapping(value = "/rest/book")
public class BookController {

    private final AppService svc;

    @Autowired
    public BookController(AppService svc) {
        this.svc = svc;
    }

    @GetMapping
    public Page<Book> find(@PageableDefault(value = 15, sort = { "description" }, direction = Sort.Direction.ASC) Pageable pageable){
        return svc.getBookService().findAll(pageable);
    }

    @GetMapping(value = "/{id}/article")
    public Page<Article> getArticle(@PageableDefault(value = 15, sort = { "crtdate" }, direction = Sort.Direction.DESC) Pageable pageable,
                                    @PathVariable(value = "id") Integer id){
        return svc.getArticleService().findByBook_Id(pageable,id);
    }
}
