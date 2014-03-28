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
import metier.Discipline;
import metier.Education;
import metier.School;

/**
 *
 * @author biron
 */
public class EducationDAO implements IDAO<Education> {

  @Override
  public boolean insert(Education pEducation) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `education`(`name`, `nb_hours`, `promo`, `id_school`) VALUES (?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pEducation.getName());
      stat.setInt(2, pEducation.getNbHours());
      stat.setInt(3, pEducation.getPromo());
      stat.setInt(4, pEducation.getSchool().getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean update(Education pEducation) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `education` SET `name`=?,`nb_hours`=?,`promo`=?,`id_school`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pEducation.getName());
      stat.setInt(2, pEducation.getNbHours());
      stat.setInt(3, pEducation.getPromo());
      stat.setInt(4, pEducation.getSchool().getId());
      
      stat.setInt(5, pEducation.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Education objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Education selectById(int id) {
    Education education = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `education` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la school
        /////////////////////////////////////////////////////////////////////////
        School school = null;
        
        education = new Education(res.getInt("id"), res.getString("name"),
                                  res.getInt("nb_hours"), res.getInt("promo"),
                                  school);
        
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return education;
  }

  @Override
  public List<Education> selectAll() {
    List<Education> listEducations = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `education`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la school
        /////////////////////////////////////////////////////////////////////////
        School school = null;
        
        Education education = new Education(res.getInt("id"), res.getString("name"),
                                            res.getInt("nb_hours"), res.getInt("promo"),
                                            school);
        listEducations.add(education);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listEducations;
  }
  
  
  
  /**
   * Retourne toutes les formations (Education) appartenant à une école (School)
   * 
   * @param pSchoolId
   * @return 
   */
  public List<Education> selectAllBySchoolId(int pSchoolId) {
    List<Education> listEducations = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `education` WHERE `id_school`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pSchoolId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la school (on a l'id)
        /////////////////////////////////////////////////////////////////////////
        School school = null;
        
        Education education = new Education(res.getInt("id"), res.getString("name"),
                                            res.getInt("nb_hours"), res.getInt("promo"),
                                            school);
        listEducations.add(education);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listEducations;
  }
  
}
