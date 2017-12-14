package org.jys.learn.spring.jpa.springdata.service.article.book;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ysjiang on 2017/12/13.
 *
 * @author Jiang YueSong
 * @date 2017/12/13
 */
public interface BookRepository extends JpaRepository<Book,Integer> {

}
