package org.jys.server.site.service.article;

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

    List<Article> findAll();

    Article save(Article article);


}
