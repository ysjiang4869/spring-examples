package org.jys.learn.spring.hibernate.service.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by allen on 6/8/2016.
 *
 */
@Entity
@Table(name = "task")
public class JxTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private UUID uuid;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date deadline;
    private int objectstate;  //0: not start 1: under doing 9:finished
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date crtdate;


    public void init(String id, String description)
    {
        this.init(id, description, null);
    }

    public void init(String id, String description, Date deadline)
    {
        this.setId(id);
        this.setDescription(description);
        this.setDeadline(deadline);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getObjectstate() {
        return objectstate;
    }

    public void setObjectstate(int objectstate) {
        this.objectstate = objectstate;
    }

    public Date getCrtdate() {
        return crtdate;
    }

    public void setCrtdate(Date crtdate) {
        this.crtdate = crtdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
