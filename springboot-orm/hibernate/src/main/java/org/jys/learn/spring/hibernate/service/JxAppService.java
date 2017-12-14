package org.jys.learn.spring.hibernate.service;

import org.jys.learn.spring.hibernate.service.dao.JxHibernateDao;
import org.jys.learn.spring.hibernate.service.task.JxTaskService;
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
    private final JxHibernateDao jxHibernateDao;

    @Autowired
    public JxAppService(JxHibernateDao jxHibernateDao){

        this.jxHibernateDao = jxHibernateDao;
    }


    private JxTaskService newTaskService(){

        JxTaskService task=new JxTaskService();
        try{
            //此处切换不同的orm dao，即可实现采用不同的orm工具
            task.init(jxHibernateDao);
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
