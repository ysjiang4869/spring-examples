package com.pinebud.examples.springbootjpa.service.dao;

import com.pinebud.examples.springbootjpa.service.task.JxTask;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 */
@Repository
public class JxJpaDao implements JiTaskDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<JxTask> find() {
        return em.createQuery("from JxTask order by creattime desc").getResultList();
    }

    @Override
    public void add(JxTask task) {
       em.persist(task);
    }

    @Override
    public JxTask get(String id) {
        return null;
    }

    @Override
    public void set(JxTask task) {

    }

    @Override
    public void delete(String id) {

    }
}
