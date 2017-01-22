package com.pinebud.examples.springboothibernate.service.dao;

import com.pinebud.examples.springboothibernate.service.dao.JiTaskDao;
import com.pinebud.examples.springboothibernate.service.task.JxTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        return (List<JxTask>) sessionFactory.getCurrentSession().createQuery("from JxTask order by creattime desc").list();
    }

    @Override
    public void add(JxTask task) {
        Session session=sessionFactory.getCurrentSession();
        session.save(task);
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
