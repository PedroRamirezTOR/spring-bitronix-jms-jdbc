package com.example.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bitronix.tm.resource.jdbc.PoolingDataSource;

/**
 * TransportPlan data base configuration class.
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
public class TransportationPlanDBConfig {

  /**
   * Log file
   */
  private static final Logger LOGGER = LoggerFactory.getLogger("ppalfile");

  /**
   * Driver class name
   */
  @Value("${tp.jdbc.driverClassName}")
  private String driverClassName;

  /**
   * User name
   */
  @Value("${tp.jdbc.username}")
  private String username;

  /**
   * Url
   */
  @Value("${tp.jdbc.url}")
  private String url;

  /**
   * Password
   */
  @Value("${tp.jdbc.password}")
  private String password;

  /**
   * C3p0 pool max size attribute
   */
  @Value("${tp.c3p0.max_size}")
  private int c3p0MaxSize;

  /**
   * C3p0 pool min size attribute
   */
  @Value("${tp.c3p0.min_size}")
  private int c3p0MinSize;

  /**
   * C3p0 pool unreturned connection time out attribute
   */
  @Value("${tp.c3p0.unreturned_connection_timeout}")
  private int c3p0UnreturnedConnectionTimeout;

  /**
   * c3p0 Acquire Increment
   */
  @Value("${tp.c3p0.acquire_increment}")
  private int c3p0AcquireIncrement;

  /**
   * C3p0 pool max idle time attribute
   */
  @Value("${tp.c3p0.max_idle_time}")
  private int c3p0MaxIdleTime;

  /**
   * Default constructor method
   */
  public TransportationPlanDBConfig() {
    // Empty constructor
  }

  /**
   * TransportationPlan database datasource creation method
   * @param env
   * @return vds
   */
  @Bean(name = "tpds", destroyMethod = "close")
  public DataSource dataSource() {
    LOGGER.debug("Creating Transportation plan DS");
    PoolingDataSource poolingDataSource = new PoolingDataSource();
    poolingDataSource.setClassName(driverClassName);
    poolingDataSource.setUniqueName("tpds");
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
   * Create JdbcTemplate bean
   * @param dataSource
   * @return tpJdbcTemplate
   */
  @Bean(name = "tpJdbcTemplate")
  JdbcTemplate jdbcTemplate(@Qualifier("tpds") DataSource dataSource) {
    LOGGER.debug("Creating JdbcTemplate transport plan");
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    LOGGER.debug(" JdbcTemplate Transport Plan created ");
    return jdbcTemplate;
  }

}
