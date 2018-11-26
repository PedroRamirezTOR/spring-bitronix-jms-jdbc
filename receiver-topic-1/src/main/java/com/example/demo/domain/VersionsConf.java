package com.example.demo.domain;

import java.sql.Timestamp;

/**
 * Versionsconf class
 */
public class VersionsConf {

  /**
   * Version id
   */
  private long versionid;

  /**
   * DB name
   */
  private String dbname;

  /**
   * DB Descripction
   */
  private String description;

  /**
   * State
   */
  private boolean state;

  /**
   * Creation date
   */
  private Timestamp date;

  /**
   * ctc ID
   */
  private long ctcid;

  /**
   * Class constructor
   * @param dbname
   * @param description
   * @param state
   * @param date
   * @param ctcid
   */
  public VersionsConf(long versionid, String dbname, String description, boolean state, Timestamp date, long ctcid) {
    this(dbname, description, state, date, ctcid);
    this.versionid = versionid;
  }

  /**
   * Class constructor
   * @param dbname
   * @param description
   * @param state
   * @param date
   * @param ctcid
   */
  public VersionsConf(String dbname, String description, boolean state, Timestamp date, long ctcid) {
    this.dbname = dbname;
    this.description = description;
    this.state = state;
    this.date = new Timestamp(date.getTime());
    this.ctcid = ctcid;
  }

  /**
   * Default constructor
   */
  public VersionsConf() {
    // Default constructor
  }

  /**
   * Get the versionid
   */
  public long getVersionid() {
    return versionid;
  }

  /**
   * Set the versionid
   * @param versionid
   */
  public void setVersionid(long versionid) {
    this.versionid = versionid;
  }

  /**
   * Get the dbname
   */
  public String getDbname() {
    return dbname;
  }

  /**
   * Set the dbname
   * @param dbname
   */
  public void setDbname(String dbname) {
    this.dbname = dbname;
  }

  /**
   * Get the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description
   * @param description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get the state
   */
  public boolean isState() {
    return state;
  }

  /**
   * Set the state
   * @param state
   */
  public void setState(boolean state) {
    this.state = state;
  }

  /**
   * Get the date
   */
  public Timestamp getDate() {
    return new Timestamp(date.getTime());
  }

  /**
   * Set the date
   * @param date
   */
  public void setDate(Timestamp date) {
    this.date = new Timestamp(date.getTime());
  }

  /**
   * Get the ctcid
   */
  public long getCtcid() {
    return ctcid;
  }

  /**
   * Set the ctcid
   * @param ctcid
   */
  public void setCtcid(long ctcid) {
    this.ctcid = ctcid;
  }

  @Override
  public String toString() {
    return "[VersionsConf: versionid:" + versionid + " dbname:" + dbname + " description:" + description
            + " state:" + state + " date:" + date + " ctcid:" + ctcid + "]";
  }

}
