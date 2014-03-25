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
  public void insert(Education pEducation) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`education`(`name`, `nb_hours`, `promo`, `id_school`) VALUES (?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pEducation.getName());
      stat.setInt(2, pEducation.getNbHours());
      stat.setInt(3, pEducation.getPromo());
      stat.setInt(4, pEducation.getSchool().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void update(Education pEducation) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`education` SET `name`=?,`nb_hours`=?,`promo`=?,`id_school`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pEducation.getName());
      stat.setInt(2, pEducation.getNbHours());
      stat.setInt(3, pEducation.getPromo());
      stat.setInt(4, pEducation.getSchool().getId());
      
      stat.setInt(5, pEducation.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void delete(Education objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Education selectById(int id) {
    Education education = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `campus_bdd`.`education` WHERE  `id` = ?;";
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
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la liste de discipline
        /////////////////////////////////////////////////////////////////////////
        List<Discipline> listDisciplines = new ArrayList();
        
        
        education = new Education(res.getInt("id"), res.getString("name"),
                                  res.getInt("nb_hours"), res.getInt("promo"),
                                  school, listDisciplines);
        
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

      String sql = "SELECT * FROM `campus_bdd`.`education`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la school
        /////////////////////////////////////////////////////////////////////////
        School school = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la liste de discipline
        /////////////////////////////////////////////////////////////////////////
        List<Discipline> listDisciplines = new ArrayList();
        
        Education education = new Education(res.getInt("id"), res.getString("name"),
                                            res.getInt("nb_hours"), res.getInt("promo"),
                                            school, listDisciplines);
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
