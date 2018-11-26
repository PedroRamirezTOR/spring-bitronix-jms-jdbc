package com.example.demo.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.example.demo.dao.VersionsConfDao;
import com.example.demo.domain.VersionsConf;



/**
 * VersionsConf dao class.
 * 
 * It allow to access to the table VersionsConf in topology and configuration data base
 * 
 * @author PedroRamirez
 *
 */
@Component
public class VersionsConfDaoImpl implements VersionsConfDao {

  /**
   * Logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger("ppalfile");

  /**
   * Create simpleJdbcInsert from JdbcTemplate
   * @return
   */
  protected SimpleJdbcInsert newSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
    return new SimpleJdbcInsert(jdbcTemplate);
  }

  /**
   * VersionsConf insert method
   * @param jdbcTemplate
   * @param versionsConf
   * @return versionsConf inserted
   */
  private VersionsConf insert(JdbcTemplate jdbcTemplate, VersionsConf versionsConf) {
    VersionsConf versionsConfResult = versionsConf;
    try {
      SimpleJdbcInsert simplejdbc
          = newSimpleJdbcInsert(jdbcTemplate).withTableName("versionsconf").usingGeneratedKeyColumns("versionid");
      SqlParameterSource parameters = new BeanPropertySqlParameterSource(versionsConf);
      Number number = simplejdbc.executeAndReturnKey(parameters);
      versionsConfResult.setVersionid(number.longValue());
    } catch (DataAccessException e) {
      versionsConfResult = null;
      LOGGER.debug("Error inserting versionsConf", e);
    }
    return versionsConfResult;
  }

  /**
   * VersionsConf updated method
   * @param jdbcTemplate
   * @param versionsConf
   * @return versionsConf updated
   */
  private static VersionsConf update(JdbcTemplate jdbcTemplate, VersionsConf versionsConf) {
    VersionsConf versionsConfResult = versionsConf;
    try {
      String sql = "update versionsConf set dbname=?, description=?, state=?, date=?, ctcid=? where versionid=?";
      LOGGER.debug(sql);
      int affectedRows = jdbcTemplate.update(sql, versionsConf.getDbname(), versionsConf.getDescription(),
          versionsConf.isState(), versionsConf.getDate(), versionsConf.getCtcid(), versionsConf.getVersionid());
      if (affectedRows == 0) {
        // No rows updated
        LOGGER.error("Error updating versionsConf. No rows affected.");
        versionsConfResult = null;
      } else {
        // Row updated
        LOGGER.debug("Updated versionsConf. Affected rows: {}", affectedRows);
      }
    } catch (DataAccessException e) {
      versionsConfResult = null;
      LOGGER.debug("Error updating versionsConf", e);
    }
    return versionsConfResult;
  }

  /**
   * VersionsConf insert or update method
   * @param jdbcTemplate
   * @param versionsconf
   * @return versionsConfResult inserted o updated
   */
  @Override
  public VersionsConf insertUpdate(JdbcTemplate jdbcTemplate, VersionsConf versionsConf) {
    VersionsConf versionsConfResult;
    if (versionsConf.getVersionid() > 0) {
      // If versionsConf has id, then we are going to update object in database
      versionsConfResult = update(jdbcTemplate, versionsConf);
    } else {
      // In other case we are going to insert a new versionsConf
      versionsConfResult = insert(jdbcTemplate, versionsConf);
    }
    return versionsConfResult;
  }

  /**
   * VersionsConf delete method
   * @param jdbcTemplate
   * @param versionsConf
   * @return true - deleted or false - error
   */
  @Override
  public boolean delete(JdbcTemplate jdbcTemplate, VersionsConf versionsConf) {
    boolean result = true;
    try {
      String sql = "delete from versionsconf where versionid=?";
      LOGGER.debug(sql);
      int affectedRows = jdbcTemplate.update(sql, versionsConf.getVersionid());
      if (affectedRows == 0) {
        // No rows deleted
        LOGGER.error("Error updating versionsConf. No rows affected.");
      } else {
        // Row deleted
        LOGGER.debug("Updated versionsConf. Affected rows: {}", affectedRows);
      }
    } catch (DataAccessException e) {
      // Error during delete
      result = false;
      LOGGER.debug("Error deleting versionsConf", e);
    }
    return result;
  }

  /**
   * VersionsConf findAll method
   * @param jdbcTemplate
   * @return list with all versions conf in database
   */
  @Override
  public List<VersionsConf> findAll(JdbcTemplate jdbcTemplate) {
    List<VersionsConf> versionsConfs = null;
    try {
      String sql = "select * from versionsconf";
      LOGGER.debug(sql);
      versionsConfs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<VersionsConf>(VersionsConf.class));
    } catch (DataAccessException e) {
      LOGGER.debug("Error select all versionsConf.", e);
    }
    return versionsConfs;
  }

  /**
   * VersionsConf find by id method
   * @param jdbcTemplate
   * @param id
   * @return found versionsconf
   */
  @Override
  public VersionsConf findById(JdbcTemplate jdbcTemplate, long id) {
    VersionsConf versionsConfs = null;
    try {
      String sql = "select * from versionsconf where versionid=?";
      LOGGER.debug(sql);
      versionsConfs = jdbcTemplate.queryForObject(sql, new Object[] { id },
          new BeanPropertyRowMapper<VersionsConf>(VersionsConf.class));
    } catch (DataAccessException e) {
      LOGGER.debug("Error finding by id versionsConf.", e);
    }
    return versionsConfs;
  }
}
