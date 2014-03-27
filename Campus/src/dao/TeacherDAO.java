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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Education;
import metier.Lesson;
import metier.School;
import metier.Teacher;

/**
 *
 * @author biron
 */
public class TeacherDAO implements IDAO<Teacher>{
  
  private static final int ID_ROLE_TEACHER = 2;

  @Override
  public void insert(Teacher pTeacher) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_promo`, `id_school`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pTeacher.getBirthDate().getTime());
      
      stat.setString(1, pTeacher.getLogin());
      stat.setString(2, pTeacher.getPasswd());
      stat.setString(3, pTeacher.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pTeacher.getFirstName());
      stat.setString(6, pTeacher.getLastName());
      stat.setInt(7, pTeacher.getPhone());
      stat.setInt(8, ID_ROLE_TEACHER);
      stat.setString(9, null);  // null, car un teacher n'appartient à aucune promo
      stat.setInt(10, pTeacher.getSchool().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void update(Teacher pTeacher) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pTeacher.getBirthDate().getTime());
      
      stat.setString(1, pTeacher.getLogin());
      stat.setString(2, pTeacher.getPasswd());
      stat.setString(3, pTeacher.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pTeacher.getFirstName());
      stat.setString(6, pTeacher.getLastName());
      stat.setInt(7, pTeacher.getPhone());
      
      stat.setInt(8, pTeacher.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void delete(Teacher objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Teacher selectById(int id) {
    Teacher teacher = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `user` WHERE  `id` = ? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      stat.setInt(2, ID_ROLE_TEACHER);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        // On récupère l'education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On récupère la liste des Lesson pour ce Teacher
        LessonDAO lessonDao = new LessonDAO();
        List<Lesson> listLessons = lessonDao.selectAllByTeacherId(res.getInt("id"));
      
          teacher = new Teacher(res.getInt("id"), res.getString("login"),
                              res.getString("pwd"), res.getString("mail"),
                              res.getDate("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              school, education,
                              listLessons);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return teacher;
  }
  
  public Teacher selectByLoginPwd(String pLogin, String pPwd) {
    Teacher teacher = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `campus_bdd`.`user` WHERE `login`=? AND `pwd`=? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setString(1, pLogin);
      stat.setString(2, pPwd);
      stat.setInt(3, ID_ROLE_TEACHER);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        // On récupère l'education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On récupère la liste des Lesson pour ce Teacher
        LessonDAO lessonDao = new LessonDAO();
        List<Lesson> listLessons = lessonDao.selectAllByTeacherId(res.getInt("id"));
      
          teacher = new Teacher(res.getInt("id"), res.getString("login"),
                              res.getString("pwd"), res.getString("mail"),
                              res.getDate("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              school, education,
                              listLessons);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return teacher;
  }

  @Override
  public List<Teacher> selectAll() {
    List<Teacher> listTeachers = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_TEACHER);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        // On récupère l'education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On récupère la liste des Lesson pour ce Teacher
        LessonDAO lessonDao = new LessonDAO();
        List<Lesson> listLessons = lessonDao.selectAllByTeacherId(res.getInt("id"));
        
        Teacher teacher = new Teacher(res.getInt("id"), res.getString("login"),
                                    res.getString("pwd"), res.getString("mail"),
                                    res.getDate("birth_date"), res.getString("first_name"),
                                    res.getString("last_name"), res.getInt("phone"),
                                    school, education,
                                    listLessons);
        listTeachers.add(teacher);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listTeachers;
  }
  
  
  public List<Teacher> selectAllBySchoolId(int pSchoolId) {
    List<Teacher> listTeachers = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ? AND `id_school` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_TEACHER);
      stat.setInt(2, pSchoolId);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        // On récupère l'education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        // On récupère la liste des Lesson pour ce Teacher
        LessonDAO lessonDao = new LessonDAO();
        List<Lesson> listLessons = lessonDao.selectAllByTeacherId(res.getInt("id"));
        
        Teacher teacher = new Teacher(res.getInt("id"), res.getString("login"),
                                    res.getString("pwd"), res.getString("mail"),
                                    res.getDate("birth_date"), res.getString("first_name"),
                                    res.getString("last_name"), res.getInt("phone"),
                                    school, education,
                                    listLessons);
        listTeachers.add(teacher);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listTeachers;
  }
  
}
