package com.pinebud.examples.springboothibernate.service;

import com.pinebud.examples.springboothibernate.service.dao.JxHibernateDao;
import com.pinebud.examples.springboothibernate.service.dao.JxJpaDao;
import com.pinebud.examples.springboothibernate.service.task.JxTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
@Service("svc")
@Scope("singleton")
public class JxAppService {

    private volatile static JxAppService singleton = null;
    private static final Logger log = LoggerFactory.getLogger(JxAppService.class);

    public JxAppService(){

    }
    protected JxTaskService task;


    public static JxAppService getInstance() {
        if (singleton == null) {
            synchronized (JxAppService.class) {
                if (singleton == null)
                    singleton = new JxAppService();
            }
        }
        return singleton;
    }



    @Autowired
    private JxHibernateDao jxHibernateDao;

    @Autowired
    private JxJpaDao jxJpaDao;

    public JxTaskService newTaskService(){

        JxTaskService task=new JxTaskService();
        try{
            task.init(jxJpaDao); //此处切换不同的orm dao，即可实现采用不同的orm工具
        }
        catch (Exception e){
            task=null;
        }
        return task;
    }

    public JxTaskService getTaskService(){
        if (this.task == null)
        {
            this.task = newTaskService();
        }
        return this.task;
    }
}
