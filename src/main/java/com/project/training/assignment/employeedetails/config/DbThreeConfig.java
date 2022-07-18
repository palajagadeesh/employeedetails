package com.project.training.assignment.employeedetails.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef ="db3EntityManagerFactory",
        transactionManagerRef ="db3TransactionManager",
        basePackages = "com.project.training.assignment.employeedetails.repository.user")
public class DbThreeConfig {
    @Bean
    @ConfigurationProperties(prefix = "db3.datasource")
    public DataSource db3DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean db3EntityManagerFactory(EntityManagerFactoryBuilder emfb){
        HashMap<String,Object> properties=new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","create");
        properties.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        return emfb.dataSource(db3DataSource())
                .packages("com.project.training.assignment.employeedetails.model.user")
                .properties(properties)
                .build();
    }
    @Bean
    public PlatformTransactionManager db3TransactionManager(@Qualifier("db3EntityManagerFactory") EntityManagerFactory entityManagerFactory){
        return  new JpaTransactionManager(entityManagerFactory);
    }

}
