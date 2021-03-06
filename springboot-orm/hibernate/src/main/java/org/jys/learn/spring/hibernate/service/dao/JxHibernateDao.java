package org.jys.learn.spring.hibernate.service.dao;


import org.jys.learn.spring.hibernate.service.task.JxTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 *
 */
@Repository
public class JxHibernateDao implements JiTaskDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public JxHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List find() {
        return  sessionFactory.getCurrentSession().createQuery("from JxTask order by crtdate desc").list();
    }

    @Override
    public void add(JxTask task) {
        Session session=sessionFactory.getCurrentSession();
        session.save(task);
    }

    @Override
    public JxTask get(String id) {
        Session session=sessionFactory.getCurrentSession();
        return null;
    }

    @Override
    public void set(JxTask task) {
        Session session=sessionFactory.getCurrentSession();
        session.merge(task);
    }

    @Override
    public void delete(String id) {
        Session session=sessionFactory.getCurrentSession();
        JxTask item=this.get(id);
        session.delete(item);
    }
}
