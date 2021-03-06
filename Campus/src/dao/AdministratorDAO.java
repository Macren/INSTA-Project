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
import metier.Administrator;
import metier.School;

/**
 *
 * @author biron
 */
public class AdministratorDAO implements IDAO<Administrator>{
  
  private static final int ID_ROLE_ADMINISTRATOR = 1;

  public AdministratorDAO() {
  }
  
  public AdministratorDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }
  
  @Override
  public int insert(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `user` (`login`,`pwd`,`mail`,`birth_date`,`first_name`,`last_name`,`phone`,`path_img_trombi`,`id_role`,`id_school`) VALUES (?,?,?,?,?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pAdministrator.getBirthDate().getTime());
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      stat.setString(8, pAdministrator.getPathImgTrombi());
      stat.setInt(9, ID_ROLE_ADMINISTRATOR);
      stat.setInt(10, pAdministrator.getSchool().getId());
      
      stat.executeUpdate();
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=?, `path_img_trombi`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String birthDate = sdf.format(pAdministrator.getBirthDate().getTime());
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      stat.setString(4, birthDate);
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      stat.setString(8, pAdministrator.getPathImgTrombi());
      
      stat.setInt(9, pAdministrator.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }
  

  @Override
  public Administrator selectById(int id) {
    Administrator administrator = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `user` WHERE  `id` = ? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      stat.setInt(2, ID_ROLE_ADMINISTRATOR);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                          res.getString("pwd"), res.getString("mail"),
                                          res.getDate("birth_date"), res.getString("first_name"),
                                          res.getString("last_name"), res.getInt("phone"),
                                          res.getString("path_img_trombi"), school, null); // dernier arg : Education
                                          // Un admin n'a pas de formation (Education)
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return administrator;
  }
  
  
  
  public Administrator selectByLoginPwd(String pLogin, String pPwd) {
    Administrator administrator = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `user` WHERE `login`=? AND `pwd`=? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setString(1, pLogin);
      stat.setString(2, pPwd);
      stat.setInt(3, ID_ROLE_ADMINISTRATOR);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                          res.getString("pwd"), res.getString("mail"),
                                          res.getDate("birth_date"), res.getString("first_name"),
                                          res.getString("last_name"), res.getInt("phone"),
                                          res.getString("path_img_trombi"), school, null); // dernier arg : Education
                                          // Un admin n'a pas de formation (Education)
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return administrator;
  }
  
  
  @Override
  public List<Administrator> selectAll() {
    List<Administrator> listAdministrators = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_ADMINISTRATOR);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        Administrator administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                                      res.getString("pwd"), res.getString("mail"),
                                                      res.getDate("birth_date"), res.getString("first_name"),
                                                      res.getString("last_name"), res.getInt("phone"),
                                                      res.getString("path_img_trombi"), school, null); // dernier arg : Education
                                                    // Un admin n'a pas de formation (Education)
        listAdministrators.add(administrator);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listAdministrators;
  }
  
  
  @Override
  public boolean delete(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `user` WHERE `id`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pAdministrator.getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }
  
  public List<Administrator> selectAllBySchoolId(int pSchoolId) {
    List<Administrator> listAdmin = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `user` WHERE `id_role` = ? AND `id_school` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, ID_ROLE_ADMINISTRATOR);
      stat.setInt(2, pSchoolId);
      
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        // On récupère l'école en fonction de son id
        SchoolDAO schoolDao = new SchoolDAO();
        School school = schoolDao.selectById(res.getInt("id_school"));
        
        Administrator teacher = new Administrator(res.getInt("id"), res.getString("login"),
                                    res.getString("pwd"), res.getString("mail"),
                                    res.getDate("birth_date"), res.getString("first_name"),
                                    res.getString("last_name"), res.getInt("phone"),
                                    res.getString("path_img_trombi"), school, null);
        listAdmin.add(teacher);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listAdmin;
  }
  
}
