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

import com.example.demo.dao.TrainDao;
import com.example.demo.domain.Train;

/**
 * train dao class.
 * 
 * It allows to access to the table train transportation plan and configuration data base
 * @author AlvaroAbad
 *
 */
@Component
public class TrainDaoImpl implements TrainDao {

  private static final Logger LOGGER = LoggerFactory.getLogger("ppalfile");

  /**
   * Create simpleJdbcInsert from JdbcTemplate
   * @return SimpleJdbcInsert
   */
  protected SimpleJdbcInsert newSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
    return new SimpleJdbcInsert(jdbcTemplate);
  }

  /**
   * Inserts a in train table and sets auto generated key register number
   * 
   * Log a message at the DEBUG or ERROR level
   * 
   * 
   * @param jdbcTemplate
   * @param train
   * @return the inserted train object
   */
  private Train insert(JdbcTemplate jdbcTemplate, Train train) {
    Train trainResult = train;
    try {
      SimpleJdbcInsert simplejdbc = newSimpleJdbcInsert(jdbcTemplate).withTableName("train").usingGeneratedKeyColumns(
          "registernumber");
      SqlParameterSource parameters = new BeanPropertySqlParameterSource(train);
      Number number = simplejdbc.executeAndReturnKey(parameters);
      trainResult.setRegisternumber(number.longValue());
    } catch (DataAccessException e) {
      trainResult = null;
      LOGGER.debug("Error inserting train", e);
    }
    return trainResult;
  }

  /**
   * Issue a single SQL update operation via a prepared statement, binding the given arguments
   * 
   * @param jdbcTemplate
   * @param train
   * @return the updated train
   * @throws DataAccessException
   */
  private static Train update(JdbcTemplate jdbcTemplate, Train train) {
    Train trainResult = train;
    try {
      String sql = "update train set changefortrain=?, productid=?, enterpriseid=?, respectschedule=?,"
          + " routing=?, atpmode=? where registernumber=?";
      LOGGER.debug(sql);
      int affectedRows = jdbcTemplate.update(sql, train.getChangefortrain(), train.getProductid(),
          train.getEnterpriseid(), train.isRespectschedule(), train.isRouting(), train.getAtpmode(),
          train.getRegisternumber());
      if (affectedRows == 0) {
        LOGGER.error("Error updating train. No rows affected.");
        trainResult = null;
      } else {
        LOGGER.debug("Updated train. Affected rows: {}", affectedRows);
      }
    } catch (DataAccessException e) {
      trainResult = null;
      LOGGER.debug("Error updating train", e);
    }
    return trainResult;
  }

  /**
   * updates/inserts row in train table
   * 
   * checks if exists the train parameter before issuing the SQL operation
   * 
   * @param jdbcTemplate
   * @param train
   * @return updated train
   */
  @Override
  public Train insertUpdate(JdbcTemplate jdbcTemplate, Train train) {
    Train result;
    if (train.getRegisternumber() > 0) {
      result = update(jdbcTemplate, train);
    } else {
      result = insert(jdbcTemplate, train);
    }
    return result;
  }

  /**
   * Removes row with the registernumber primary key given
   * @param jdbcTemplate
   * @param registernumber
   * @return deleted/not deleted
   * @throws DataAccessException
   */
  @Override
  public boolean delete(JdbcTemplate jdbcTemplate, Train train) {
    boolean result = true;
    try {
      String sql = "delete from train where registernumber=?";
      LOGGER.debug(sql);
      int affectedRows = jdbcTemplate.update(sql, train.getRegisternumber());
      if (affectedRows == 0) {
        LOGGER.error("Error deleting train. No rows affected.");
      } else {
        LOGGER.debug("Deleted train. Affected rows: {}", affectedRows);
      }
    } catch (DataAccessException e) {
      result = false;
      LOGGER.debug("Error deleting train", e);
    }
    return result;
  }

  /**
   * Returns all rows from train table
   * 
   * Sql query: select * from train
   * 
   * maps each row and stores it in an train list element
   * @param jdbcTemplate
   * @return List of trains (all rows)
   * @throws DataAccessException
   */
  @Override
  public List<Train> findAll(JdbcTemplate jdbcTemplate) {
    List<Train> trains = null;
    try {
      String sql = "select * from train";
      LOGGER.debug(sql);
      trains = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Train>(Train.class));
    } catch (DataAccessException e) {
      LOGGER.debug("Error inserting train.", e);
    }
    return trains;
  }

  /**
   * Looks for rows with the indicated registernumber primary key
   * 
   * sql query: select * from train where registernumber = (registernumber parameter)
   * 
   * maps each row and stores it in a train list element
   * 
   * @param jdbTemplate
   * @param registernumber (sql primary key)
   * @return List of trains (all rows found)
   * @throws DataAccessExcdeption
   * 
   */
  @Override
  public Train findById(JdbcTemplate jdbcTemplate, long id) {
    Train trains = null;
    try {
      String sql = "select * from train where registernumber=?";
      LOGGER.debug(sql);
      trains = jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Train>(Train.class));
    } catch (DataAccessException e) {
      LOGGER.debug("Error finding by register number train.", e);
    }
    return trains;
  }

}
