package org.jys.learn.spring.jpa.hibernate.service.dao;

import org.jys.learn.spring.jpa.hibernate.service.task.JxTask;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 *
 */
@Repository
public class JxJpaDao implements JiTaskDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List find() {
        return em.createQuery("from JxTask order by crtdate desc").getResultList();
    }

    @Override
    public void add(JxTask task) {
       em.persist(task);
    }

    @Override
    public JxTask get(String id) {
        return em.find(JxTask.class,id);
    }

    @Override
    public void set(JxTask task) {
        em.merge(task);
    }

    @Override
    public void delete(String id) {
        JxTask item=this.get(id);
        if(item!=null) {
            em.remove(item);
        }
    }
}
