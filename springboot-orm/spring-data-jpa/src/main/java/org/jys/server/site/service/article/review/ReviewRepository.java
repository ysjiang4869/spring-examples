package org.jys.server.site.service.article.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ysjiang on 2017/12/8.
 *
 */
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findAll();

    Review save(Review tag);
}
