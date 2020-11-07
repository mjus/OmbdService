package com.movie.ombdwebservice.configuration;

import com.movie.ombdwebservice.model.Movie;
import com.movie.ombdwebservice.model.tesicnor.MovieToSend;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.movie.ombdwebservice.repository.tesicnor",
        entityManagerFactoryRef = "tesicnorEntityManagerFactory",
        transactionManagerRef= "tesicnorTransactionManager")
public class TesicnorDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.tesicnor")
    public DataSourceProperties tesicnorDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.tesicnor.configuration")
    public DataSource tesicnorDataSource() {
        return tesicnorDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "tesicnorEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tesicnorEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tesicnorDataSource())
                .packages(Movie.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager tesicnorTransactionManager(
            final @Qualifier("tesicnorEntityManagerFactory") LocalContainerEntityManagerFactoryBean tesicnorEntityManagerFactory) {
        return new JpaTransactionManager(tesicnorEntityManagerFactory.getObject());
    }
}
