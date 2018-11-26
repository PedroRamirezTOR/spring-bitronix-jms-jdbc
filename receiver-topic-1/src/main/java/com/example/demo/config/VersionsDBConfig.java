package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bitronix.tm.resource.jdbc.PoolingDataSource;

/**
 * Versions data base configuration class.
 * 
 * This is a configuration Spring Class.
 * 
 * Configuration properties are loaded from application.properties.
 * 
 * @author PedroRamirez
 *
 */
@Configuration
@EnableTransactionManagement
public class VersionsDBConfig {

  /**
   * Log attribute
   */
  private static final Logger LOGGER = LoggerFactory.getLogger("ppalfile");

  /**
   * Url attribute
   */
  @Value("${v.jdbc.url}")
  private String url;

  /**
   * Driver class name attribute
   */
  @Value("${v.jdbc.driverClassName}")
  private String driverClassName;

  /**
   * User name attribute
   */
  @Value("${v.jdbc.username}")
  private String username;

  /**
   * C3p0 acquire increment attribute
   */
  @Value("${v.c3p0.acquire_increment}")
  private int c3p0AcquireIncrement;

  /**
   * Password attribute
   */
  @Value("${v.jdbc.password}")
  private String password;

  /**
   * C3p0 pool max idle time attribute
   */
  @Value("${v.c3p0.max_idle_time}")
  private int c3p0MaxIdleTime;

  /**
   * C3p0 pool min size attribute
   */
  @Value("${v.c3p0.min_size}")
  private int c3p0MinSize;

  /**
   * C3p0 pool max size attribute
   */
  @Value("${v.c3p0.max_size}")
  private int c3p0MaxSize;

  /**
   * C3p0 pool unreturned connection time out attribute
   */
  @Value("${v.c3p0.unreturned_connection_timeout}")
  private int c3p0UnreturnedConnectionTimeout;

  /**
   * Default constructor method
   */
  public VersionsDBConfig() {
    // Empty constructor
  }

  /**
   * Versions database datasource creation method
   * @param env
   * @return vds
   */
  @Primary
  @Bean(name = "vds", destroyMethod = "close")
  public DataSource dataSource() {
    LOGGER.debug("Creating Versions DS");
    PoolingDataSource poolingDataSource = new PoolingDataSource();
    poolingDataSource.setClassName(driverClassName);
    poolingDataSource.setUniqueName("vds");
    Properties props = new Properties();
    props.put("url", url);
    props.put("user", username);
    props.put("password", password);
    poolingDataSource.setDriverProperties(props);
    poolingDataSource.setAllowLocalTransactions(true);
    poolingDataSource.setMaxPoolSize(c3p0MaxSize);
    poolingDataSource.init();
    return poolingDataSource;
  }

  /**
   * Versions database jdbc template creation method
   * @param datasource
   * @return versionsJdbcTemplate
   */
  @Primary
  @Bean(name = "versionJdbcTemplate")
  public JdbcTemplate jdbcTemplate(@Qualifier("vds") DataSource dataSource) {
    LOGGER.debug("Creando JdbcTemplate Versiones");
    JdbcTemplate jdbcTemp = new JdbcTemplate(dataSource);
    LOGGER.debug("Creado JdbcTemplate Versiones");
    return jdbcTemp;
  }

}
