package my.assignments;

import com.google.common.eventbus.EventBus;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Simple Spring config using spring-boot to power up a Restful interace that would be the trigger 
 * for all the Events, which makes this a main Event publishing application. 
 * Created by chrissekaran on 04/02/15.
 */
@EnableIntegration
@EnableAutoConfiguration
@ActiveProfiles("embedded")
@EnableConfigurationProperties(EventSourceConfiguration.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "my.assignments.repo")
@ComponentScan(basePackages = "my.assignments")
@PropertySource("classpath:db.properties")
public class EventSourceConfiguration {

    @Value("${db.dialect}")
    private String dbDialect;

    @Value("${db.driver}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Autowired
    private Environment environment;



    @Bean
    @Profile("embedded")
    public DataSource embeddedDataSource()  {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .build();

    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource embeddedDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(embeddedDataSource);
        emf.setPackagesToScan("my.assignments.domain");
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.afterPropertiesSet();
        return emf;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        //jpaVendorAdapter.setDatabasePlatform(dbDialect);
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setGenerateDdl(true);

        return jpaVendorAdapter;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    
    @Bean public EventBus eventBus() {
        return new EventBus();
    }

    public static void main(String[] args) throws IOException   {

        SpringApplication.run(EventSourceConfiguration.class, args);

        System.out.println("\n\tType 'q' to exit...\n");
            while('q' != System.in.read()) {
        }

        System.exit(0);

    }

    public void setDbDialect(String dbDialect) {
        this.dbDialect = dbDialect;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
