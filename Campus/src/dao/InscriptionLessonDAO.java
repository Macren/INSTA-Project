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
import metier.InscriptionLesson;
import metier.Lesson;
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
      
      String sql = "INSERT INTO `campus_bdd`.`inscription_lesson`(`id_user_student`, `id_lesson`, `inscription_date`, `desinscription_date`, `admin_validation`) VALUES (?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateInscription = sdf.format(pInscriptionLesson.getDateInscription().getTime());
      String dateDesinscription = sdf.format(pInscriptionLesson.getDateDesinscription().getTime());
      
      stat.setInt(1, pInscriptionLesson.getStudent().getId());
      stat.setInt(2, pInscriptionLesson.getLesson().getId());
      stat.setString(3, dateInscription);
      stat.setString(4, dateDesinscription);
      stat.setBoolean(5, pInscriptionLesson.isAdminValidation());
      
      stat.executeUpdate();
      // On récupère le dernier id généré
      ResultSet rs = stat.getGeneratedKeys();
      if(rs != null && rs.first()){
        long generatedId = rs.getLong(1);
        return (int)generatedId;
      }
      
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
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`inscription_lesson` SET `inscription_date`=?,`desinscription_date`=?,`admin_validation`=? WHERE `id_user_student`=? AND `id_lesson`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateInscription = sdf.format(pInscriptionLesson.getDateInscription().getTime());
      String dateDesinscription = sdf.format(pInscriptionLesson.getDateDesinscription().getTime());
      
      stat.setString(1, dateInscription);
      stat.setString(2, dateDesinscription);
      stat.setBoolean(3, pInscriptionLesson.isAdminValidation());
      
      stat.setInt(4, pInscriptionLesson.getStudent().getId());
      stat.setInt(5, pInscriptionLesson.getLesson().getId());
      
      stat.executeUpdate();
      return true;
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
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
    List<InscriptionLesson> listInscriptionLessons = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();

      String sql = "SELECT * FROM `campus_bdd`.`inscription_lesson`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le Student
        /////////////////////////////////////////////////////////////////////////
        Student student = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la Lesson
        /////////////////////////////////////////////////////////////////////////
        Lesson lesson = null;
        
        InscriptionLesson inscriptionLesson = new InscriptionLesson(student, lesson,
                                                                    res.getDate("inscription_date"), res.getDate("desinscription_date"),
                                                                    res.getBoolean("admin_validation"));
        listInscriptionLessons.add(inscriptionLesson);
      }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(InscriptionLessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return listInscriptionLessons;
  }
  
}
