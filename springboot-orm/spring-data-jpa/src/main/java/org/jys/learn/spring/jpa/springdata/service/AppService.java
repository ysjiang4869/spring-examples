package org.jys.learn.spring.jpa.springdata.service;

import org.jys.learn.spring.jpa.springdata.service.article.ArticleRepository;
import org.jys.learn.spring.jpa.springdata.service.article.book.BookRepository;
import org.jys.learn.spring.jpa.springdata.service.article.comment.CommentRepository;
import org.jys.learn.spring.jpa.springdata.service.article.tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ysjiang on 2017/12/7.
 *
 */
@Service("svc")
public class AppService {

    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AppService(TagRepository tagRepository, ArticleRepository articleRepository, CommentRepository commentRepository,BookRepository bookRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    public TagRepository getTagService() {
        return tagRepository;
    }

    public ArticleRepository getArticleService() {
        return articleRepository;
    }

    public CommentRepository getCommentService() {
        return commentRepository;
    }

    public BookRepository getBookService() {
        return bookRepository;
    }
}
