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
import metier.School;
import metier.Teacher;

/**
 *
 * @author biron
 */
public class TeacherDAO implements IDAO<Teacher>{
  
  private static final int ID_ROLE_TEACHER = 2;
  
  public TeacherDAO() {
  }
  
  public TeacherDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }

  @Override
  public int insert(Teacher pTeacher) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `user`(`login`,`pwd`,`mail`,`birth_date`,`first_name`,`last_name`,`phone`,`path_img_trombi`,`id_role`,`id_school`,`id_education`) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
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
      stat.setString(8, pTeacher.getPathImgTrombi());
      stat.setInt(9, ID_ROLE_TEACHER);
      stat.setInt(10, pTeacher.getSchool().getId());
      stat.setString(11, null);  // null, car un teacher n'a aucune Education (formation)
      
      stat.executeUpdate();
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Teacher pTeacher) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=?,`path_img_trombi`=? WHERE `id`=?;";
      
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
      stat.setString(8, pTeacher.getPathImgTrombi());
      
      stat.setInt(9, pTeacher.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }

  @Override
  public boolean delete(Teacher pTeacher) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `user` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pTeacher.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
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
        
        teacher = new Teacher(res.getInt("id"), res.getString("login"),
                            res.getString("pwd"), res.getString("mail"),
                            res.getDate("birth_date"), res.getString("first_name"),
                            res.getString("last_name"), res.getInt("phone"),
                            res.getString("path_img_trombi"), school, null); // dernier arg : Education
                                          // Un admin n'a pas de formation (Education)
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
      
      String sql = "SELECT * FROM `user` WHERE `login`=? AND `pwd`=? AND `id_role` = ?;";
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
        
        teacher = new Teacher(res.getInt("id"), res.getString("login"),
                            res.getString("pwd"), res.getString("mail"),
                            res.getDate("birth_date"), res.getString("first_name"),
                            res.getString("last_name"), res.getInt("phone"),
                            res.getString("path_img_trombi"), school, null);
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
        
        Teacher teacher = new Teacher(res.getInt("id"), res.getString("login"),
                                    res.getString("pwd"), res.getString("mail"),
                                    res.getDate("birth_date"), res.getString("first_name"),
                                    res.getString("last_name"), res.getInt("phone"),
                                    res.getString("path_img_trombi"), school, null);
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
        
        Teacher teacher = new Teacher(res.getInt("id"), res.getString("login"),
                                    res.getString("pwd"), res.getString("mail"),
                                    res.getDate("birth_date"), res.getString("first_name"),
                                    res.getString("last_name"), res.getInt("phone"),
                                    res.getString("path_img_trombi"), school, null);
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
