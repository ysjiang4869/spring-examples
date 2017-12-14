package org.jys.learn.spring.jpa.springdata.service.article;

import org.jys.learn.spring.jpa.springdata.service.article.comment.ArticleWithTitleAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Created by ysjiang on 2017/12/7.
 *
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,UUID>,JpaSpecificationExecutor<Article> {

    Page<Article> findByTags_Id(Pageable pageable, Integer id);

    List<Article> findByTags_Id(Integer id);

    Page<Article> findByBook_Id(Pageable pageable, Integer id);

    Page<Article> findByObjectstate(Pageable pageable, short state);

    ArticleWithTitleAndId findByComments_Id(Integer id);

}
