package com.mm.emp.dal.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = {"com.mm.emp.model","com.mm.emp.dal.repository"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE)
        }
)
public class PostgresConfig {

    private String jdbcUrl="jdbc:postgresql://localhost:5432/emp";
    private String pgUserName="postgres";
    private String pgPassword="root";
    private String pgDriverClassName="org.postgresql.Driver";

    @Bean(name = "primaryDatasource")
    public DataSource dataSource(){
        BasicDataSource dataSource= (BasicDataSource) DataSourceBuilder.create()
                .url(jdbcUrl)
                .driverClassName(pgDriverClassName)
                .username(pgUserName)
                .password(pgPassword)
                .build();
        return dataSource;
    }

    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
     @Qualifier("primaryDatasource") DataSource primaryDS){
        return builder.dataSource(primaryDS)
        .packages("com.mm.emp.model")
        .build();

    }

    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory")EntityManagerFactory primaryEntityManagerFactory){
            return  new JpaTransactionManager(primaryEntityManagerFactory);
    }

}
