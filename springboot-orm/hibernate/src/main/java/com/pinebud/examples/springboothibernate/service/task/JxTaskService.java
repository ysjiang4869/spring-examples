package com.pinebud.examples.springboothibernate.service.task;

import com.pinebud.examples.springboothibernate.service.dao.JiTaskDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/10/25 0025.
 * 目前来看，JdbcTemplate的优点是可以将数据源定义为连接池等，省略了sql异常的处理
 * 暂时采用该版本，原版本依旧保留，update和add方法可以使用jdbcTemplate的方法进一步改进，不用拼sql语句
 */
public class JxTaskService {

    private JiTaskDao taskDao;

    public JxTaskService() {

    }

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
        JxTask ret=null;

        return ret;
    }


    public void set(String id, JxTask item) {

    }


    public JxTask add(JxTask item) {
        taskDao.add(item);
        return item;
    }

    public void remove(String id) {

    }
}
