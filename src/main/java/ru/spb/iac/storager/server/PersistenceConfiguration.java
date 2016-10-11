package ru.spb.iac.storager.server;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class PersistenceConfiguration {

    @Bean
    @Profile("hsql")
    public DataSource dataSourceHsql() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.HSQL)
                .generateUniqueName(true)
                .build();
    }

    @Bean
    @Profile("oracle.deployed")
    public DataSource dataSourceOracleDeployed() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource("jdbc/storager");
    }

    @Bean
    @Profile("oracle.standalone")
    public DataSource dataSourceOracleStandalone() {
        throw new UnsupportedOperationException();
    }

    @Bean
    @Profile("hsql")
    public HibernateJpaVendorAdapter vendorAdapterHsql() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setGenerateDdl(true);
        return vendorAdapter;
    }

    @Bean
    @Profile({"oracle.deployed", "oracle.standalone"})
    public HibernateJpaVendorAdapter vendorAdapterOracle() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, HibernateJpaVendorAdapter vendorAdapter) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(this.getClass().getPackage().getName());
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
