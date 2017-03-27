package com.pinebud.examples.springbootshiro.service;

import com.pinebud.examples.springbootshiro.service.security.service.JxDataServcice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 *
 */
@Service("svc")
@Scope("singleton")
public class JxAppService {
    private static final Logger log= LoggerFactory.getLogger(JxAppService.class);
    private JxDataServcice data;
    private final DataSource dataSource;

    @Autowired
    public JxAppService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private JxDataServcice newDataService(){
        JxDataServcice dataServcice=new JxDataServcice();
        try {
            dataServcice.init(dataSource);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            dataServcice=null;
        }
        return dataServcice;
    }

    public JxDataServcice getDataService(){
        if(this.data==null){
            this.data=this.newDataService();
        }
        return this.data;
    }
}
