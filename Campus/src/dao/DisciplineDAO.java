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

/**
 *
 * @author biron
 */
public class DisciplineDAO implements IDAO<Discipline> {

  private static final int DISCIPLINE_STATUS_AVAILABLE = 1;
  private static final int DISCIPLINE_STATUS_FULL = 2;
  
  @Override
  public void insert(Discipline pDiscipline) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`discipline`(`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES (?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pDiscipline.getName());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(2, null); //pDiscipline.getBeginDate());
      stat.setString(3, null); //pDiscipline.getEndDate());
      stat.setInt(4, pDiscipline.getEducation().getId());
      
      switch (pDiscipline.getStatus()) {
        case "AVAILABLE":
          stat.setInt(5, DISCIPLINE_STATUS_AVAILABLE);
          break;
        case "FULL":
          stat.setInt(5, DISCIPLINE_STATUS_FULL);
          break;
        default:
          stat.setInt(5, 0); // pDiscipline.getStatus.getId();
          break;
      }
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void update(Discipline pDiscipline) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`discipline` SET `name`=?,`begin_date`=?,`end_date`=?,`id_education`=?,`id_discipline_status`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pDiscipline.getName());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(2, null); //pDiscipline.getBeginDate());
      stat.setString(3, null); //pDiscipline.getEndDate());
      stat.setInt(4, pDiscipline.getEducation().getId());
      
      switch (pDiscipline.getStatus()) {
        case "AVAILABLE":
          stat.setInt(5, DISCIPLINE_STATUS_AVAILABLE);
          break;
        case "FULL":
          stat.setInt(5, DISCIPLINE_STATUS_FULL);
          break;
        default:
          stat.setInt(5, 0); // pDiscipline.getStatus.getId();
          break;
      }
      
      stat.setInt(6, pDiscipline.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void delete(Discipline objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Discipline selectById(int id) {
    Discipline discipline = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `campus_bdd`.`discipline` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer l' Education
        /////////////////////////////////////////////////////////////////////////
        Education education = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le status
        /////////////////////////////////////////////////////////////////////////
        String status = null;
        
        
        discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                    null, null, //res.getInt("begin_date"), res.getInt("end_date"),
                                    education, status);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return discipline;
  }

  @Override
  public List<Discipline> selectAll() {
    List<Discipline> listDisciplines = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `campus_bdd`.`discipline`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer l' Education
        /////////////////////////////////////////////////////////////////////////
        Education education = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le status
        /////////////////////////////////////////////////////////////////////////
        String status = null;
        
        Discipline discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                                null, null, //res.getInt("begin_date"), res.getInt("end_date"),
                                                education, status);
        listDisciplines.add(discipline);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listDisciplines;
  }
  
}
