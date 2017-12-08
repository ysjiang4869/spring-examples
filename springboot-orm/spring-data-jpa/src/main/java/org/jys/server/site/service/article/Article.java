package org.jys.server.site.service.article;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.jys.server.site.service.article.review.Review;
import org.jys.server.site.service.article.tag.Tag;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ysjiang on 2017/12/7.
 *
 */
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(generator ="UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String title;

    private String titlePicture;

    private String digest;

    private String body;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime crtdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime upddate;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "article_tag",
            joinColumns = {@JoinColumn(name = "articleid",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tagid",referencedColumnName = "id")})
    private List<Tag> tags=new ArrayList<>();

    /**
     * save type:text/markdown/html
     */
    private short type;

    @OneToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "article_review",
            joinColumns = {@JoinColumn(name = "articleid",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "reviewid",referencedColumnName = "id")})
    private List<Review> reviews=new ArrayList<>();

    private int praise;

    /**
     * 0-draft; 1-publishing; 9-deleted
     */
    private short objectstate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(LocalDateTime crtdate) {
        this.crtdate = crtdate;
    }

    public LocalDateTime getUpddate() {
        return upddate;
    }

    public void setUpddate(LocalDateTime upddate) {
        this.upddate = upddate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public short getObjectstate() {
        return objectstate;
    }

    public void setObjectstate(short objectstate) {
        this.objectstate = objectstate;
    }
}
