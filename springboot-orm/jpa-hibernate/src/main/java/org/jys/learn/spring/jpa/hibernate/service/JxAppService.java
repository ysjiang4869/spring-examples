package org.jys.learn.spring.jpa.hibernate.service;

import org.jys.learn.spring.jpa.hibernate.service.dao.JxJpaDao;
import org.jys.learn.spring.jpa.hibernate.service.task.JxTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/16 0016.
 *
 */
@Service("svc")
@Scope("singleton")
public class JxAppService {

    private static final Logger log = LoggerFactory.getLogger(JxAppService.class);

    private JxTaskService task;


    private final JxJpaDao jxJpaDao;

    @Autowired
    public JxAppService(JxJpaDao jxJpaDao) {
        this.jxJpaDao = jxJpaDao;
    }

    private JxTaskService newTaskService(){

        JxTaskService task=new JxTaskService();
        try{
            task.init(jxJpaDao); //此处切换不同的orm dao，即可实现采用不同的orm工具
        }
        catch (Exception e){
            log.error(e.getMessage(),e);
            task=null;
        }
        return task;
    }

    public JxTaskService getTaskService(){
        if (this.task == null) {
            this.task = newTaskService();
        }
        return this.task;
    }
}
