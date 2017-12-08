package org.jys.server.site.service.article.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ysjiang on 2017/12/8.
 *
 *  @author Jiang YueSong
 *  @date 2017/12/8
 */
public interface TagRepository extends JpaRepository<Tag,Integer>{

    List<Tag> findAll();

    Tag save(Tag tag);
}
