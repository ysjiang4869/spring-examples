package org.jys.learn.spring.jpa.springdata.service.article.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by ysjiang on 2017/12/8.
 *
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> ,JpaSpecificationExecutor<Comment>{

    Page<Comment> findAllByParent_Id(Pageable pageable,Integer id);

    Page<Comment> findAllByParent_IdAndObjectstate(Pageable pageable,Integer id,Short objectstate);

    Page<Comment> findAllByObjectstate(Pageable pageable,Integer id);
}
