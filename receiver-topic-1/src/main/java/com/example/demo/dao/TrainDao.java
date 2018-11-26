package com.example.demo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.domain.Train;


/**
 * Train dao interface.
 * 
 * It allow to access to the table Train in transportation plan data base
 * @author AlvaroAbad
 *
 */
public interface TrainDao {
  /**
   * Train insert or update method
   * @param jdbcTemplate
   * @param Train
   * @return Train inserted o updated
   */
  Train insertUpdate(JdbcTemplate jdbcTemplate, Train train);

  /**
   * Train findAll method
   * @param jdbcTemplate
   * @return list with all Trains in database
   */
  boolean delete(JdbcTemplate jdbctemplate, Train train);

  /**
   * Train findAll method
   * @param jdbcTemplate
   * @return list with all Trains in database
   */
  List<Train> findAll(JdbcTemplate jdbcTemplate);

  /**
   * Train find by id method
   * @param jdbcTemplate
   * @param id
   * @return found Train
   */
  Train findById(JdbcTemplate jdbcTemplate, long id);

}
