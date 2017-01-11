package com.pinebud.examples.springboothibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by Administrator on 2016/11/21 0021.
 */
@SpringBootApplication
public class JxApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Object[]{JxApplication.class}, args);

    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/task?useUnicode=true&amp;characterEncoding=UTF-8");
        ds.setUsername("postgres");
        ds.setPassword("root");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sfb=new LocalSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setPackagesToScan("com.pinebud.examples.springboothibernate");
        Properties props=new Properties();
        props.setProperty("dialect","org.hibernate.dialect.PostgreSQL95Dialect");
        sfb.setHibernateProperties(props);
        return sfb;
    }
    /**
     * 由于hibernate是基于jpa规范实现的，所以仅使用jpa的时候需要下列设置；
     * 但是本项目已经集成了hibernate，所以jpa的EntityManagerFactory也已经配置了，可以直接使用
     * 至于对于jpa和hibernate的选择，jpa是标准的规范，可以选择不同实现（其实目前也就几个而已）
     * 而hibernate是jpa的实现之一但是功能更强大一些，一些语法是可以通用的，无非是session换成了EntityManager
     */
   /* @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQL95Dialect");
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource,JpaVendorAdapter jpaVendorAdapter){
        LocalContainerEntityManagerFactoryBean emfb=new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        emfb.setPackagesToScan("com.pinebud.examples.springboothibernate");
        return emfb;
    }*/

}
