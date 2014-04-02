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
import metier.InscriptionLesson;
import metier.Student;

/**
 *
 * @author biron
 */
public class InscriptionLessonDAO implements IDAO<InscriptionLesson> {

  @Override
  public int insert(InscriptionLesson pInscriptionLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`inscription_lesson`(`id_user_student`, `id_lesson`) VALUES (?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setInt(1, pInscriptionLesson.getStudent().getId());
      stat.setInt(2, pInscriptionLesson.getLesson().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return 0;
  }

  @Override
  public boolean update(InscriptionLesson pInscriptionLesson) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(InscriptionLesson objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override // N'a pas de sens ==> Non implémentée
  public InscriptionLesson selectById(int id) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<InscriptionLesson> selectAll() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  public List<Student> selectAllStudentsbyLessonId(int pIdLesson) {
    List<Student> listStudents = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `inscription_lesson` WHERE `id_lesson` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pIdLesson);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        
        // On récupère le student
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.selectById(res.getInt("id_user_student"));
        
        listStudents.add(student);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listStudents;
  }
  
}
