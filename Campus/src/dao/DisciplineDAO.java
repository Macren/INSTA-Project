/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import static dao.IDAO.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String beginDate = sdf.format(pDiscipline.getBeginDate().getTime());
      String endDate = sdf.format(pDiscipline.getEndDate().getTime());
      
      stat.setString(1, pDiscipline.getName());
      stat.setString(2, beginDate);
      stat.setString(3, endDate);
      stat.setInt(4, pDiscipline.getEducation().getId());
      
      switch (pDiscipline.getStatus()) {
        case "Disponible":
          stat.setInt(5, DISCIPLINE_STATUS_AVAILABLE);
          break;
        case "Complet":
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
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String beginDate = sdf.format(pDiscipline.getBeginDate().getTime());
      String endDate = sdf.format(pDiscipline.getEndDate().getTime());
      
      stat.setString(1, pDiscipline.getName());
      stat.setString(2, beginDate);
      stat.setString(3, endDate);
      stat.setInt(4, pDiscipline.getEducation().getId());
      
      switch (pDiscipline.getStatus()) {
        case "Disponible":
          stat.setInt(5, DISCIPLINE_STATUS_AVAILABLE);
          break;
        case "Complet":
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
      
      String sql = "SELECT * FROM `campus_bdd`.`discipline` WHERE `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère l'Education (la formation) de la Discipline (matiere)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `campus_bdd`.`discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = "";
        while (resStatus.next())
            status = resStatus.getString("label");
        
        
        discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
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
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `campus_bdd`.`discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = "";
        while (resStatus.next())
            status = resStatus.getString("label");
        
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
  
  
  
  /**
   * Retourne toutes les matières (Discipline) appartenant à une formation (Education)
   * 
     * @param pEduId
   * @return 
   */
  public List<Discipline> selectAllByEducationId(int pEduId) {
    List<Discipline> listDiscipline = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `campus_bdd`.`discipline` WHERE `id_education`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pEduId);
        System.out.println("before res");
      ResultSet res = stat.executeQuery();
        System.out.println("after res / before eduDAO");
        
      // On récupère l'éducation
      EducationDAO eduDAO = new EducationDAO();
      Education edu = eduDAO.selectById(pEduId);
        System.out.println("after eduDAO / before while (res)");
      while (res.next()) {
          
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `campus_bdd`.`discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        int tmp = res.getInt("id_discipline_status");
        statStatus.setInt(1, tmp);
        ResultSet resStatus = statStatus.executeQuery();
        String status = "";
        while (resStatus.next())
            status = resStatus.getString("label");
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la liste de matière
        /////////////////////////////////////////////////////////////////////////
        
        Discipline discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                            res.getDate("begin_date"), res.getDate("end_date"),
                                            edu, status);
        listDiscipline.add(discipline);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(EducationDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listDiscipline;
  }
  
}
