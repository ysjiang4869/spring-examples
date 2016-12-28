package com.pinebud.examples.springbootshiro.service;

import com.pinebud.examples.springbootshiro.service.security.service.JxDataServcice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by jiangyuesong on 2016/12/26 0026.
 */
@Service("svc")
@Scope("singleton")
public class JxAppService {
    private JxDataServcice data;


    @Autowired
    private DataSource dataSource;
    public JxDataServcice newDataService(){
        JxDataServcice dataServcice=new JxDataServcice();
        try {
            dataServcice.init(dataSource);
        }catch (Exception e){
            e.printStackTrace();
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
