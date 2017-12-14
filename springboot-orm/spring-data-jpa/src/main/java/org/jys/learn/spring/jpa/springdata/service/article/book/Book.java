package org.jys.learn.spring.jpa.springdata.service.article.book;

import javax.persistence.*;

/**
 * Created by ysjiang on 2017/12/13.
 *
 * @author Jiang YueSong
 * @date 2017/12/13
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private short objectstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getObjectstate() {
        return objectstate;
    }

    public void setObjectstate(short objectstate) {
        this.objectstate = objectstate;
    }
}
