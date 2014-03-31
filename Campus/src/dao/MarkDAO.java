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
import metier.Mark;
import metier.Student;
import metier.Teacher;

/**
 *
 * @author biron
 */
public class MarkDAO implements IDAO<Mark> {
  
  public MarkDAO() {
  }
  
  public MarkDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }

  @Override
  public int insert(Mark pMark) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `mark`(`value`, `value_max`, `id_user_student`, `id_user_teacher`, `id_discipline`, `comment`) VALUES (?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      stat.setFloat(1, pMark.getValue());
      stat.setFloat(2, pMark.getValueMax());
      stat.setInt(3, pMark.getStudent().getId());
      stat.setInt(4, pMark.getTeacher().getId());
      stat.setInt(5, pMark.getDiscipline().getId());
      stat.setString(6, pMark.getComment());
      
      stat.executeUpdate();
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Mark pMark) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `mark` SET `value`=?,`value_max`=?,`id_user_student`=?,`id_user_teacher`=?,`id_discipline`=?,`comment`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setFloat(1, pMark.getValue());
      stat.setFloat(2, pMark.getValueMax());
      stat.setInt(3, pMark.getStudent().getId());
      stat.setInt(4, pMark.getTeacher().getId());
      stat.setInt(5, pMark.getDiscipline().getId());
      stat.setString(6, pMark.getComment());
      
      stat.setInt(7, pMark.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Mark pMark) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `mark` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pMark.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public Mark selectById(int id) {
    Mark mark = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `mark` WHERE `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère le student
        StudentDAO studentDao = new StudentDAO();
        Student student = studentDao.selectById(res.getInt("id_user_student"));
        
        // On récupère le teacher
        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.selectById(res.getInt("id_user_teacher"));
        
        // On récupère la discipline
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        mark = new Mark(res.getInt("id"), res.getFloat("value"),
                        res.getFloat("value_max"), student,
                        teacher, discipline,
                        res.getString("comment"));
        
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return mark;
  }

  @Override
  public List<Mark> selectAll() {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        // On récupère le student
        StudentDAO studentDao = new StudentDAO();
        Student student = studentDao.selectById(res.getInt("id_user_student"));
        
        // On récupère le teacher
        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.selectById(res.getInt("id_user_teacher"));
        
        // On récupère la discipline
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  
  public List<Mark> selectAllByStudentId(int pStudentId) {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark` WHERE `id_user_student` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pStudentId);
      ResultSet res = stat.executeQuery();
      
      // On récupère le student
      StudentDAO studentDao = new StudentDAO();
      Student student = studentDao.selectById(pStudentId);
      
      while (res.next()) {
        
        // On récupère le teacher
        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.selectById(res.getInt("id_user_teacher"));
        
        // On récupère la discipline
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  
  public List<Mark> selectAllByTeacherId(int pTeacherId) {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark` WHERE `id_user_teacher` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pTeacherId);
      ResultSet res = stat.executeQuery();
      
      // On récupère le teacher
      TeacherDAO teacherDao = new TeacherDAO();
      Teacher teacher = teacherDao.selectById(pTeacherId);
      
      while (res.next()) {
        
        // On récupère le student
        StudentDAO studentDao = new StudentDAO();
        Student student = studentDao.selectById(res.getInt("id_user_student"));
        
        // On récupère la discipline
        DisciplineDAO disciplineDao = new DisciplineDAO();
        Discipline discipline = disciplineDao.selectById(res.getInt("id_discipline"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  public List<Mark> selectAllByDisciplineId(int pDisciplineId) {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark` WHERE `id_discipline` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      // On récupère la discipline
      DisciplineDAO disciplineDao = new DisciplineDAO();
      Discipline discipline = disciplineDao.selectById(pDisciplineId);
      
      while (res.next()) {
        
        // On récupère le student
        StudentDAO studentDao = new StudentDAO();
        Student student = studentDao.selectById(res.getInt("id_user_student"));
        
        // On récupère le teacher
        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.selectById(res.getInt("id_user_teacher"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  
  public List<Mark> selectAllByStudentIdAndDisciplineId(int pStudentId, int pDisciplineId) {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark` WHERE `id_user_student` = ? AND `id_discipline` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pStudentId);
      stat.setInt(2, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      // On récupère le student
      StudentDAO studentDao = new StudentDAO();
      Student student = studentDao.selectById(pStudentId);
      // On récupère la discipline
      DisciplineDAO disciplineDao = new DisciplineDAO();
      Discipline discipline = disciplineDao.selectById(pDisciplineId);
      
      while (res.next()) {
        
        // On récupère le teacher
        TeacherDAO teacherDao = new TeacherDAO();
        Teacher teacher = teacherDao.selectById(res.getInt("id_user_teacher"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  
  public List<Mark> selectAllByTeacherIdAndDisciplineId(int pTeacherId, int pDisciplineId) {
    List<Mark> listMarks = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `mark` WHERE `id_user_teacher` = ? AND `id_discipline` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pTeacherId);
      stat.setInt(2, pDisciplineId);
      ResultSet res = stat.executeQuery();
      
      // On récupère le teacher
      TeacherDAO teacherDao = new TeacherDAO();
      Teacher teacher = teacherDao.selectById(pTeacherId);
      // On récupère la discipline
      DisciplineDAO disciplineDao = new DisciplineDAO();
      Discipline discipline = disciplineDao.selectById(pDisciplineId);
      
      while (res.next()) {
        
        // On récupère le student
        StudentDAO studentDao = new StudentDAO();
        Student student = studentDao.selectById(res.getInt("id_user_student"));
        
        Mark mark = new Mark(res.getInt("id"), res.getFloat("value"),
                              res.getFloat("value_max"), student,
                              teacher, discipline,
                              res.getString("comment"));
        
        listMarks.add(mark);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(MarkDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listMarks;
  }
  
  
  
  
  
  
}
