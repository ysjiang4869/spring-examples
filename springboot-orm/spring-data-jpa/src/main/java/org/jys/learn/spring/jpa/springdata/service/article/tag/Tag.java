package org.jys.learn.spring.jpa.springdata.service.article.tag;

import javax.persistence.*;

/**
 * Created by ysjiang on 2017/12/7.
 *
 */
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private short objectstate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
