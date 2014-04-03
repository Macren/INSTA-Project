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
import metier.Education;
import metier.School;
import metier.Student;

/**
 *
 * @author biron
 */
public class StudentDAO implements IDAO<Student> {
  
  private static final int ID_ROLE_STUDENT = 3;
  
  public StudentDAO() {
  }
  
  public StudentDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }

  @Override
  public int insert(Student pStudent) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `user` (`login`,`pwd`,`mail`,`birth_date`,`first_name`,`last_name`,`phone`,`path_img_trombi`,`id_role`,`id_school`,`id_education`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pStudent.getBirthDate().getTime());
      
      stat.setString(1, pStudent.getLogin());
      stat.setString(2, pStudent.getPasswd());
      stat.setString(3, pStudent.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pStudent.getFirstName());
      stat.setString(6, pStudent.getLastName());
      stat.setInt(7, pStudent.getPhone());
      stat.setString(8, pStudent.getPathImgTrombi());
      stat.setInt(9, ID_ROLE_STUDENT);
      stat.setInt(10, pStudent.getSchool().getId());
      stat.setInt(11, pStudent.getEducation().getId());
      
      stat.executeUpdate();
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Student pStudent) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=?,`path_img_trombi`=?,`id_education`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pStudent.getBirthDate().getTime());
      
      stat.setString(1, pStudent.getLogin());
      stat.setString(2, pStudent.getPasswd());
      stat.setString(3, pStudent.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pStudent.getFirstName());
      stat.setString(6, pStudent.getLastName());
      stat.setInt(7, pStudent.getPhone());
      stat.setString(8, pStudent.getPathImgTrombi());
      stat.setInt(9, pStudent.getEducation().getId());
      
      stat.setInt(10, pStudent.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Student pStudent) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `user` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pStudent.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public Student selectById(int id) {
    Student student = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `user` WHERE  `id` = ? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      stat.setInt(2, ID_ROLE_STUDENT);
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
        
        student = new Student(res.getInt("id"), res.getString("login"),
                              res.getString("pwd"), res.getString("mail"),
                              res.getDate("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              res.getString("path_img_trombi"), school, education);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return student;
  }
  
  public Student selectByLoginPwd(String pLogin, String pPwd) {
    Student student = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `user` WHERE `login`=? AND `pwd`=? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setString(1, pLogin);
      stat.setString(2, pPwd);
      stat.setInt(3, ID_ROLE_STUDENT);
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
        
        student = new Student(res.getInt("id"), res.getString("login"),
                              res.getString("pwd"), res.getString("mail"),
                              res.getDate("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              res.getString("path_img_trombi"), school, education);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return student;
  }

  @Override
  public List<Student> selectAll() {
    List<Student> listStudents = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_STUDENT);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        // On récupère l'education (la formation)
        EducationDAO educationDao = new EducationDAO();
        Education education = educationDao.selectById(res.getInt("id_education"));
        
        Student student = new Student(res.getInt("id"), res.getString("login"),
                                      res.getString("pwd"), res.getString("mail"),
                                      res.getDate("birth_date"), res.getString("first_name"),
                                      res.getString("last_name"), res.getInt("phone"),
                                      res.getString("path_img_trombi"), school, education);
        listStudents.add(student);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listStudents;
  }
  
  public List<Student> selectAllBySchoolId(int pSchoolId) {
    List<Student> listStudent = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ? AND `id_school` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_STUDENT);
      stat.setInt(2, pSchoolId);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        Student myStudent = this.selectById(res.getInt("id"));
        listStudent.add(myStudent);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listStudent;
  }
}
