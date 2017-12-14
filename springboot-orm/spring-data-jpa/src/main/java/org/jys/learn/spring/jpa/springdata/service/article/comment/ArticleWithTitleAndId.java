package org.jys.learn.spring.jpa.springdata.service.article.comment;

import java.util.UUID;

/**
 * Created by ysjiang on 2017/12/13.
 *
 * @author Jiang YueSong
 * @date 2017/12/13
 */
public interface ArticleWithTitleAndId {

    UUID getId();

    String getTitle();
}
