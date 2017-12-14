package org.jys.learn.spring.jpa.springdata.service;

import org.jys.learn.spring.jpa.springdata.service.article.comment.Comment;
import org.jys.learn.spring.jpa.springdata.service.article.tag.Tag;
import org.jys.learn.spring.jpa.springdata.service.article.Article;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by ysjiang on 2017/12/8.
 *
 * @author Jiang YueSong
 * @date 2017/12/8
 */
public class SpecUtils {

    public static Specification<Article> getArticleSpecs(Map<String,Object> params) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Root<Tag> tagRoot=query.from(Tag.class);
            Expression<Collection<Tag>> articleTags = root.get("tags");
            for(Map.Entry<String,Object> entry:params.entrySet()){
                String key=entry.getKey();
                Object value=entry.getValue();
                if(value==null){
                    continue;
                }
                switch (key){
                    case "tag":
                        predicates.add(builder.and(builder.isMember(tagRoot,articleTags),builder.equal(tagRoot.get("id"),value)));
                        break;
                    default:
                        predicates.add(builder.equal(root.get("objectstate"),value));
                        break;
                }
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }

    public static Specification<Comment> getCommentSpecs(Map<String,Object> params) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for(Map.Entry<String,Object> entry:params.entrySet()){
                String key=entry.getKey();
                Object value=entry.getValue();
                if(value==null&&!Objects.equals(key,"parent")){
                    continue;
                }
                switch (key){
                    case "parent":
                        if(value==null){
                            predicates.add(builder.isNull(root.get("parent")));
                        }else {
                            predicates.add(builder.equal(root.get("parent").get("id"),value));
                        }
                        break;
                    default:
                        predicates.add(builder.equal(root.get(key),value));
                        break;
                }
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
