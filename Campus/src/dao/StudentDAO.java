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
import metier.Lesson;
import metier.Student;

/**
 *
 * @author biron
 */
public class StudentDAO implements IDAO<Student> {
  
  private static final int ID_ROLE_STUDENT = 3;

  @Override
  public void insert(Student pStudent) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_promo`, `id_school`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pStudent.getLogin());
      stat.setString(2, pStudent.getPasswd());
      stat.setString(3, pStudent.getMail());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(4, pStudent.getBirthDate());
      stat.setString(5, pStudent.getFirstName());
      stat.setString(6, pStudent.getLastName());
      stat.setInt(7, pStudent.getPhone());
      stat.setInt(8, ID_ROLE_STUDENT);
      stat.setInt(9, 0); // pStudent.getPromo().getId() ///////////////// !!!!!
      stat.setInt(10, pStudent.getSchool().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void update(Student pStudent) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=?,`id_promo`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pStudent.getLogin());
      stat.setString(2, pStudent.getPasswd());
      stat.setString(3, pStudent.getMail());
      // pour la modification des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(4, pStudent.getBirthDate());
      stat.setString(5, pStudent.getFirstName());
      stat.setString(6, pStudent.getLastName());
      stat.setInt(7, pStudent.getPhone());
      stat.setInt(8, 0); // pStudent.getPromo().getId() ///////////////// !!!!!
      
      stat.setInt(9, pStudent.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void delete(Student objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Student selectById(int id) {
    Student student = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      /////////////////////////////////////////////////////////////////////////
      // Ici récupérer la liste des lessons du student
      /////////////////////////////////////////////////////////////////////////
      List<Lesson> listLessons = new ArrayList();
      
      /////////////////////////////////////////////////////////////////////////
      // Ici récupérer l'id de l'école en fonction 
      /////////////////////////////////////////////////////////////////////////
      String sql = "SELECT * FROM `user` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        student = new Student(res.getInt("id"), res.getString("login"),
                              res.getString("password"), res.getString("mail"),
                              res.getString("birth_date"), res.getString("first_name"),
                              res.getString("last_name"), res.getInt("phone"),
                              null, null, null); // avant dernier arg education id education.. ?pb classe metier?
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
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la liste des lessons du student
        /////////////////////////////////////////////////////////////////////////
        List<Lesson> listLessons = new ArrayList();
        
        Student student = new Student(res.getInt("id"), res.getString("login"),
                                      res.getString("password"), res.getString("mail"),
                                      res.getString("birth_date"), res.getString("first_name"),
                                      res.getString("last_name"), res.getInt("phone"),
                                      null, null, null); // avant dernier arg education id education.. ?pb classe metier?
        listStudents.add(student);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listStudents;
  }
  
}
