package com.movie.ombdwebservice.configuration;

import com.movie.ombdwebservice.model.Movie;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.movie.ombdwebservice.repository.home",
        entityManagerFactoryRef = "homeEntityManagerFactory",
        transactionManagerRef= "homeTransactionManager")
public class HomeDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.home")
    public DataSourceProperties homeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.home.configuration")
    public DataSource homeDataSource() {
        return homeDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "homeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean homeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(homeDataSource())
                .packages(Movie.class)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager homeTransactionManager(
            final @Qualifier("homeEntityManagerFactory") LocalContainerEntityManagerFactoryBean homeEntityManagerFactory) {
        return new JpaTransactionManager(homeEntityManagerFactory.getObject());
    }
}
