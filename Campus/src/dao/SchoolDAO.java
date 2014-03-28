/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.School;

/**
 *
 * @author biron
 */
public class SchoolDAO implements IDAO<School> {

  public SchoolDAO() {
  }
  
  SchoolDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }
  
  @Override
  public boolean insert(School pSchool) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `school`(`name`) VALUES (?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pSchool.getName());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean update(School pSchool) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `school` SET `name`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pSchool.getName());
      
      stat.setInt(2, pSchool.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(School objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public School selectById(int id) {
    School school = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `school` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        school = new School(res.getInt("id"), res.getString("name"));
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return school;
  }

  @Override
  public List<School> selectAll() {
    List<School> listSchools = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `school`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        School school = new School(res.getInt("id"), res.getString("name"));
        
        listSchools.add(school);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(SchoolDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listSchools;
  }
  
}
