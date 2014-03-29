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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Discipline;
import metier.Lesson;
import metier.Teacher;

/**
 *
 * @author biron
 */
public class LessonDAO implements IDAO<Lesson>{

  private static final int LESSON_STATUS_AVAILABLE  = 1;
  private static final int LESSON_STATUS_FULL       = 2;
  private static final int LESSON_STATUS_CANCEL     = 3;
  private static final int LESSON_STATUS_ENDED      = 4;
  
  public LessonDAO() {
  }
  
  public LessonDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }
  
  @Override
  public int insert(Lesson pLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `lesson`(`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `nb_max_student`, `id_discipline`, `id_user_teacher`, `id_lesson_status`) VALUES (?,?,?,?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String beginDate = sdf.format(pLesson.getBeginDate().getTime());
      String endDate = sdf.format(pLesson.getEndDate().getTime());
      
      stat.setString(1, pLesson.getName());
      stat.setBoolean(2, pLesson.isTp());
      stat.setBoolean(3, pLesson.isTest());
      stat.setString(4, beginDate);
      stat.setString(5, endDate);
      stat.setInt(6, pLesson.getNbMaxStudent());
      stat.setInt(7, pLesson.getDiscipline().getId());
      stat.setInt(8, pLesson.getTeacher().getId());
      
      switch (pLesson.getStatus()) {
        case "Disponible":
          stat.setInt(9, LESSON_STATUS_AVAILABLE);
          break;
        case "Complet":
          stat.setInt(9, LESSON_STATUS_FULL);
          break;
        case "Annulé":
          stat.setInt(9, LESSON_STATUS_CANCEL);
          break;
        case "Terminé":
          stat.setInt(9, LESSON_STATUS_ENDED);
          break;
        default:
          stat.setInt(9, 0); // pDiscipline.getStatus.getId();
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
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Lesson pLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `lesson` SET `name`=?,`is_tp`=?,`is_test`=?,`begin_date`=?,`end_date`=?,`nb_max_student`=?,`id_discipline`=?,`id_user_teacher`=?,`id_lesson_status`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String beginDate = sdf.format(pLesson.getBeginDate().getTime());
      String endDate = sdf.format(pLesson.getEndDate().getTime());
      
      stat.setString(1, pLesson.getName());
      stat.setBoolean(2, pLesson.isTp());
      stat.setBoolean(3, pLesson.isTest());
      stat.setString(4, beginDate);
      stat.setString(5, endDate);
      stat.setInt(6, pLesson.getNbMaxStudent());
      stat.setInt(7, pLesson.getDiscipline().getId());
      stat.setInt(8, pLesson.getTeacher().getId());
      
      switch (pLesson.getStatus()) {
        case "Disponible":
          stat.setInt(9, LESSON_STATUS_AVAILABLE);
          break;
        case "Complet":
          stat.setInt(9, LESSON_STATUS_FULL);
          break;
        case "Annulé":
          stat.setInt(9, LESSON_STATUS_CANCEL);
          break;
        case "Terminé":
          stat.setInt(9, LESSON_STATUS_ENDED);
          break;
        default:
          stat.setInt(9, 0); // pDiscipline.getStatus.getId();
          break;
      }
      
      stat.setInt(10, pLesson.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Lesson pLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `lesson` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pLesson.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public Lesson selectById(int id) {
    Lesson lesson = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `lesson` WHERE `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        lesson = new Lesson(res.getInt("id"), res.getString("name"),
                            res.getBoolean("is_tp"), res.getBoolean("is_test"),
                            res.getInt("nb_max_student"),
                            res.getDate("begin_date"), res.getDate("end_date"),
                            status , teacher,
                            discipline);
        
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return lesson;
  }

  @Override
  public List<Lesson> selectAll() {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
  
  
  public List<Lesson> selectAllByTeacherId(int pTeacherId) {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson` WHERE `id_user_teacher` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pTeacherId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
  
  
  public List<Lesson> selectAllByDisciplineId(int pDisciplineId) {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson` WHERE `id_discipline` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
  
  
  
  public List<Lesson> selectAllLessonsByDisciplineId(int pDisciplineId) {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson` WHERE `id_discipline` = ? AND `is_tp` = ? AND `is_test` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDisciplineId);
      stat.setBoolean(2, false);
      stat.setBoolean(3, false);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
  
  
  public List<Lesson> selectAllTpsByDisciplineId(int pDisciplineId) {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson` WHERE `id_discipline` = ? AND `is_tp` = 1;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
  
  
  public List<Lesson> selectAllTestsByDisciplineId(int pDisciplineId) {
    List<Lesson> listLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `lesson` WHERE `id_discipline` = ? AND `is_test` = 1;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère le status de la lesson
        String sqlRecupStatus = "SELECT * FROM `lesson_status` WHERE `id` =?;";
        PreparedStatement statStatus = cnx.prepareStatement(sqlRecupStatus);
        statStatus.setInt(1, res.getInt("id_lesson_status"));
        ResultSet resStatus = statStatus.executeQuery();
        
        String status = null;
        while (resStatus.next()){
          status = resStatus.getString("label");
        }
        
        // On récupère la discipline de la lesson
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        // On récupère le Teacher de la Lesson
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacher = teacherDAO.selectById(res.getInt("id_user_teacher"));
        
        Lesson lesson = new Lesson(res.getInt("id"), res.getString("name"),
                                    res.getBoolean("is_tp"), res.getBoolean("is_test"),
                                    res.getInt("nb_max_student"),
                                    res.getDate("begin_date"), res.getDate("end_date"),
                                    status , teacher,
                                    discipline);
        listLessons.add(lesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listLessons;
  }
  
}

