package com.example.sportsbetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.sportsbetting.repository")
public class JpaConfig {

    private static String dbUrl = "jdbc:mysql://localhost/sportsbetting_soma_makai?serverTimezone=Europe/Budapest";
    private static String username = "root";
    private static String password = "root";

    /*private static String dbUrl = "jdbc:mysql://remotemysql.com/8nKKIMpyFt?serverTimezone=Europe/Budapest";
    private static String username = "8nKKIMpyFt";
    private static String password = "iy2sRBsZxO";*/

    //creates entity manager
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.example.sportsbetting.domain"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true"); //shows the results of the queries
        properties.setProperty("hibernate.hbm2ddl.auto", "create"); //generating database to entity create-drop for testing
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); //tablename, key etc generating
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(dbUrl, username, password);
    }


}