/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thierry
 */
public class DB {

  private Connection connection;
  private String user = "root";
  private String passwd = "";
  private String url = "jdbc:mysql://localhost/campus_bdd";

  public Connection connect() throws ClassNotFoundException {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      this.connection = (Connection) DriverManager.getConnection(url, user, passwd);

    } catch (SQLException ex) {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this.connection;
  }

  public void disconnect(Connection connection) {
    try {
      connection.close();
    } catch (SQLException ex) {
      Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void setUrl(String url) {
    this.url = url;
  }

}
