package org.jys.server.site.service.article;

import org.jys.server.site.service.article.tag.Tag;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
public class ArticleSpecs {

    public static Specification<Article> getSpecs(Map<String,Object> params) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("tags"),1));
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
