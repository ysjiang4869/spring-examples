package com.pinebud.examples.springboothibernate.service.dao;

import com.pinebud.examples.springboothibernate.service.task.JxTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 */
@Repository
public class JxJpaDao implements JiTaskDao{

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<JxTask> find() {
        return emf.createEntityManager().createQuery("from JxTask").getResultList();
    }

    @Override
    public JxTask get() {
        return null;
    }

    @Override
    public void set(JxTask task) {

    }

    @Override
    public void delete(String id) {

    }
}
