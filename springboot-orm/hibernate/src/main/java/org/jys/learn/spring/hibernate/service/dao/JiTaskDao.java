package org.jys.learn.spring.hibernate.service.dao;

import org.jys.learn.spring.hibernate.service.task.JxTask;

import java.util.List;

/**
 * Created by jiangyuesong on 2017/1/11 0011.
 *
 */
public interface JiTaskDao {
    List  find();
    void add(JxTask task);
    JxTask get(String id);
    void set(JxTask task);
    void delete(String id);

}
