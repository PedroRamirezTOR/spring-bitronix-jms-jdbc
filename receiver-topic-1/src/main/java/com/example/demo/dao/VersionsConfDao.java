package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.VersionsConf;


/**
 * Versions Conf dao interface.
 * 
 * It allow to access to the table Versions Conf in Versions data base
 * @author PedroRamirez
 *
 */
public interface VersionsConfDao {

  /**
   * VersionsConf insert or update method
   * @param jdbcTemplate
   * @param versionsconf
   * @return versionsconf inserted o updated
   */
  VersionsConf insertUpdate(JdbcTemplate jdbcTemplate, VersionsConf versionsconf);

  /**
   * VersionsConf delete method
   * @param jdbcTemplate
   * @param versionsConf
   * @return true - deleted or false - error
   */
  boolean delete(JdbcTemplate jdbcTemplate, VersionsConf versionsconf);

  /**
   * VersionsConf findAll method
   * @param jdbcTemplate
   * @return list with all versions conf in database
   */
  List<VersionsConf> findAll(JdbcTemplate jdbcTemplate);

  /**
   * VersionsConf find by id method
   * @param jdbcTemplate
   * @param id
   * @return found versionsconf
   */
  VersionsConf findById(JdbcTemplate jdbcTemplate, long id);

}
