package org.jys.learn.spring.hibernate.service.task;

import org.jys.learn.spring.hibernate.service.dao.JiTaskDao;

import java.util.*;

/**
 * Created by Administrator on 2016/10/25 0025.
 *
 */
public class JxTaskService {

    private JiTaskDao taskDao;

    public void init(JiTaskDao dao){
        this.taskDao=dao;
    }

    public Map<String, JxTask> find() {
        Map<String,JxTask> ret =new LinkedHashMap<>();
        List<JxTask> tasks=taskDao.find();
        for(JxTask task:tasks){
            ret.put(task.getUuid().toString(),task);
        }
        return ret;
    }


    public JxTask get(String id) {
        return taskDao.get(id);
    }


    public void set(String id, JxTask item) {
        if(id.equals(item.getId())){
            taskDao.set(item);
        }
    }

    public JxTask add(JxTask item) {
        taskDao.add(item);
        return item;
    }

    public void remove(String id) {
        taskDao.delete(id);
    }
}
