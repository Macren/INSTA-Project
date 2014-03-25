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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Lesson;
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
      
      stat.setString(1, pTeacher.getLogin());
      stat.setString(2, pTeacher.getPasswd());
      stat.setString(3, pTeacher.getMail());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      // Pour l'instant : String /////////////////////////////////////////!!!
      stat.setString(4, pTeacher.getBirthDate());
      stat.setString(5, pTeacher.getFirstName());
      stat.setString(6, pTeacher.getLastName());
      stat.setInt(7, pTeacher.getPhone());
      stat.setInt(8, ID_ROLE_TEACHER);
      stat.setString(9, null);  // null, car un teacher n'appartient à aucune promo
      stat.setInt(10, pTeacher.getSchool().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
      
      stat.setString(1, pTeacher.getLogin());
      stat.setString(2, pTeacher.getPasswd());
      stat.setString(3, pTeacher.getMail());
      // pour la modification des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(4, pTeacher.getBirthDate());
      stat.setString(5, pTeacher.getFirstName());
      stat.setString(6, pTeacher.getLastName());
      stat.setInt(7, pTeacher.getPhone());
      
      stat.setInt(8, pTeacher.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
      /////////////////////////////////////////////////////////////////////////
      // Ici récupérer la liste des lessons
      /////////////////////////////////////////////////////////////////////////
      List<Lesson> listLessons = new ArrayList();
      /////////////////////////////////////////////////////////////////////////
      // Ici récupérer l'id de l'école en fonction 
      /////////////////////////////////////////////////////////////////////////
      //
      String sql = "SELECT * FROM `user` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        teacher = new Teacher(res.getInt("id"), res.getString("login"),
                              res.getString("password"), res.getString("mail"),
                              res.getString("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              null, null, null); // avant dernier arg education id education.. ?pb classe metier?
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la liste des lessons
        /////////////////////////////////////////////////////////////////////////
        List<Lesson> listLessons = new ArrayList();
        
        Teacher teacher = new Teacher(res.getInt("id"), res.getString("login"),
                                      res.getString("password"), res.getString("mail"),
                                      res.getString("birth_date"), res.getString("first_name"),
                                      res.getString("last_name"), res.getInt("phone"),
                                      null, null, null); // avant dernier arg education id education.. ?pb classe metier?
        listTeachers.add(teacher);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listTeachers;
  }
  
}
