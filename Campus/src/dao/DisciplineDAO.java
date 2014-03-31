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
  
  public DisciplineDAO() {
  }
  
  public DisciplineDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }
  
  @Override
  public int insert(Discipline pDiscipline) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `discipline`(`name`, `begin_date`, `end_date`, `id_education`, `id_discipline_status`) VALUES (?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
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
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Discipline pDiscipline) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `discipline` SET `name`=?,`begin_date`=?,`end_date`=?,`id_education`=?,`id_discipline_status`=? WHERE `id`=?;";
      
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
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Discipline pDiscipline) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `discipline` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDiscipline.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public Discipline selectById(int id) {
    Discipline discipline = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `discipline` WHERE `id` = ?;";
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
        String sqlRecupStatus = "SELECT * FROM `discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    education, status);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return discipline;
  }
  
  
  
  public Discipline selectByLessonId(int pLessonId) {
    Discipline discipline = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sqlLesson = "SELECT * FROM `lesson` WHERE `id` = ?;";
      PreparedStatement statLesson = cnx.prepareStatement(sqlLesson);
      statLesson.setInt(1, pLessonId);
      ResultSet resLesson = statLesson.executeQuery();
      
      // S'il y a un resultat (une lesson)
      if(resLesson.first())
      {
        // On récupère la discipline de la lesson
        discipline = this.selectById(resLesson.getInt("id_discipline"));
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
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

      String sql = "SELECT * FROM `discipline`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        // On recupère l'Education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        Discipline discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                                res.getDate("begin_date"), res.getDate("end_date"),
                                                education, status);
        listDisciplines.add(discipline);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listDisciplines;
  }
  
  
  
  /**
   * Retourne toutes les matières (Discipline) appartenant à une formation (Education)
   * 
   * @param pEducationId
   * @return 
   */
  public List<Discipline> selectAllByEducationId(int pEducationId) {
    List<Discipline> listDiscipline = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `discipline` WHERE `id_education`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pEducationId);
      ResultSet res = stat.executeQuery();
        
      // On récupère l'éducation
      EducationDAO eduDAO = new EducationDAO();
      Education education = eduDAO.selectById(pEducationId);
      
      while (res.next()) {
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        Discipline discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                            res.getDate("begin_date"), res.getDate("end_date"),
                                            education, status);
        listDiscipline.add(discipline);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listDiscipline;
  }
  
  
  
  public List<Discipline> selectAllByEducationIdAndEducationPromo(Education pEducation) {
    List<Discipline> listDiscipline = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `discipline`, `education` WHERE `education`.`id` = `discipline`.`id_education` AND `education`.`id` = ? AND `education`.`promo` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pEducation.getId());
      stat.setInt(2, pEducation.getPromo());
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
          
        // On recupère le status
        String sqlRecupStatus = "SELECT * FROM `discipline_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_discipline_status"));
        ResultSet resStatus = statStatus.executeQuery();
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        Discipline discipline = new Discipline(res.getInt("id"), res.getString("name"),
                                            res.getDate("begin_date"), res.getDate("end_date"),
                                            pEducation, status);
        listDiscipline.add(discipline);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listDiscipline;
  }
  
}
