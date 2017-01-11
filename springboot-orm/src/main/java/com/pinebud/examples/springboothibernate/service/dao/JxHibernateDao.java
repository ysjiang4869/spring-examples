package com.pinebud.examples.springboothibernate.service.dao;

import com.pinebud.examples.springboothibernate.service.dao.JiTaskDao;
import com.pinebud.examples.springboothibernate.service.task.JxTask;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 */
@Repository
public class JxHibernateDao implements JiTaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<JxTask> find() {
        return (List<JxTask>) sessionFactory.openSession().createQuery("from JxTask").list();
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
