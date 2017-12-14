package org.jys.learn.spring.jpa.springdata.service.article.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysjiang on 2017/12/13.
 *
 * @author Jiang YueSong
 * @date 2017/12/13
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="parent")
    @JsonBackReference
    private Comment parent;

    @OneToMany
    @JoinColumn(name = "parent")
    @JsonManagedReference
    private List<Comment> children=new ArrayList<>();

    private String body;

    private String name;

    private String email;

    private short objectstate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime crtdate;

    @Transient
    private ArticleWithTitleAndId article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getObjectstate() {
        return objectstate;
    }

    public void setObjectstate(short objectstate) {
        this.objectstate = objectstate;
    }

    public LocalDateTime getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(LocalDateTime crtdate) {
        this.crtdate = crtdate;
    }

    public ArticleWithTitleAndId getArticle() {
        return article;
    }

    public void setArticle(ArticleWithTitleAndId article) {
        this.article = article;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getChildren() {
        return children;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }
}
