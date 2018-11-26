package com.example.demo.domain;

/**
 * 
 * @author Alvaro Abad
 *
 */
public class Train {
  /**
   * Foreign key
   */
  private long registernumber;

  private String changefortrain;

  private long productid;

  private long enterpriseid;

  private boolean respectschedule;

  private boolean routing;

  private String atpmode;

  /**
   * 
   * @param changebytrain
   * @param enterpriseid
   * @param respectschedule
   * @param routing
   * @param atpmode
   */
  public Train(String changebytrain, long productid, long enterpriseid, boolean respectschedule,
      boolean routing,
      String atpmode) {
    this.changefortrain = changebytrain;
    this.productid = productid;
    this.enterpriseid = enterpriseid;
    this.respectschedule = respectschedule;
    this.routing = routing;
    this.atpmode = atpmode;
  }

  /**
   * 
   * @param registernumber
   * @param changebytrain
   * @param enterpriseid
   * @param respectschedule
   * @param routing
   * @param atpmode
   */
  public Train(long registernumber, String changebytrain, long productid, long enterpriseid,
      boolean respectschedule,
      boolean routing, String atpmode) {
    this(changebytrain, productid, enterpriseid, respectschedule, routing, atpmode);
    this.registernumber = registernumber;
  }

  /**
   * Default constructor
   */
  public Train() {
    // Train empty constructor
  }

  /**
   * getter method
   * @return registernumber attribute
   */
  public long getRegisternumber() {
    return registernumber;
  }

  /**
   * setter method
   * @param registernumber
   */
  public void setRegisternumber(long registernumber) {
    this.registernumber = registernumber;
  }

  /**
   * getter method
   * @return changebytrain attribute
   */
  public String getChangefortrain() {
    return changefortrain;
  }

  /**
   * setter method
   * @param changebytrain
   */
  public void setChangefortrain(String changefortrain) {
    this.changefortrain = changefortrain;
  }

  /**
   * getter method
   * @return productid attribute
   */
  public long getProductid() {
    return productid;
  }

  /**
   * setter method
   * @param productid
   */
  public void setProductid(long productid) {
    this.productid = productid;
  }

  /**
   * getter method
   * @return
   */
  public long getEnterpriseid() {
    return enterpriseid;
  }

  /**
   * setter method
   * @param commercialoperator
   */
  public void setEntrepriseid(long enterpriseid) {
    this.enterpriseid = enterpriseid;
  }

  /**
   * getter method
   * @return respectschedule attribute
   */
  public boolean isRespectschedule() {
    return respectschedule;
  }

  /**
   * setter method
   * @param respectschedule
   */
  public void setRespectschedule(boolean respectschedule) {
    this.respectschedule = respectschedule;
  }

  /**
   * getter method
   * @return routing attribute
   */
  public boolean isRouting() {
    return routing;
  }

  /**
   * setter method
   * @param routing
   */
  public void setRouting(boolean routing) {
    this.routing = routing;
  }

  /**
   * getter method
   * @return atpmode attribute
   */
  public String getAtpmode() {
    return atpmode;
  }

  /**
   * setter method
   * @param atpmode
   */
  public void setAtpmode(String atpmode) {
    this.atpmode = atpmode;
  }

}
