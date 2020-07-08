//package nghiem.nc.demohibernateenvers.config;
//
//import ch.qos.logback.core.db.dialect.PostgreSQLDialect;
//import java.util.Properties;
//import javax.persistence.SharedCacheMode;
//import javax.persistence.ValidationMode;
//import javax.sql.DataSource;
//import org.hibernate.boot.SchemaAutoTooling;
//import org.hibernate.cfg.AvailableSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//@Configuration
//public class ApplicationConfig {
//
//  @Autowired
//  private ApplicationContext applicationContext;
//
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean emf() {
//    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//    adapter.setDatabasePlatform(
//        "org.hibernate.dialect.PostgreSQLDialect"); //you can change this if you have a different DB
//    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//
//    Properties properties = new Properties();
//    properties.put("hibernate.dialect", PostgreSQLDialect.class);
//    properties.put(AvailableSettings.HBM2DDL_AUTO, SchemaAutoTooling.UPDATE.name().toLowerCase());
//    properties.put(AvailableSettings.SHOW_SQL, "true");
//    properties.put("org.hibernate.envers.audit_table_suffix",
//        "_aud");
//    properties.put("hibernate.listeners.envers.autoRegister", false);
//    properties.put("hibernate.envers.autoRegisterListeners", false);
//    factory.setJpaProperties(properties);
//    factory.setJpaVendorAdapter(adapter);
//    factory.setDataSource((DataSource) applicationContext.getBean("datasource"));
//    factory.setPackagesToScan("nghiem.nc.demohibernateenvers");
//    factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
//    factory.setValidationMode(ValidationMode.NONE);
//    return factory;
//  }
//
//}
