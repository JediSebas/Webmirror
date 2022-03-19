package com.jedisebas.webmirror;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.jedisebas.webmirror",
        entityManagerFactoryRef = "eventEntityManagerFactory",
        transactionManagerRef = "eventTransactionManager"
)
public class EventRespositoryConfiguration {
    @Autowired
    private Environment env;
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean eventEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(eventDataSource());
        em.setPackagesToScan(new String[]{"com.jedisebas.webmirror"});
        em.setPersistenceUnitName("db1EntityManager");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show-sql",
                env.getProperty("spring.jpa.show-sql"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource eventDataSource() {
        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager eventTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                eventEntityManagerFactory().getObject());
        return transactionManager;
    }
}
