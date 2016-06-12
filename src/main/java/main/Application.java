package main;

import services.*;
import javax.sql.DataSource;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan({"filters", "controllers", "dto", "config"})
@EnableJpaRepositories("entity")
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        //if (GraphicsEnvironment.isHeadless()) {
            DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", ""});
        //}
        SpringApplication.run(Application.class, args);
    }



    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().
                setType(EmbeddedDatabaseType.H2).
                addScript("db-schema.sql").
                addScript("db-test-data.sql").
                build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource());
        lef.setJpaVendorAdapter(jpaVendorAdapter());
        lef.setPackagesToScan("entity");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public InformationTicketService getInformationTicketService() {
        return new InformationTicketService();
    }
    @Bean
    public PresentationUserService getPresentationUserService() {
        return new PresentationUserService();
    }
    @Bean
    public UserService getUserService() {
        return new UserService();
    }
    @Bean
    public LoginService getLoginService() {
        return new LoginService();
    }
    @Bean
    public ReservationService getReservationService() {
        return new ReservationService ();
    }
}
