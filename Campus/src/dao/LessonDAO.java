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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Lesson;

/**
 *
 * @author biron
 */
public class LessonDAO implements IDAO<Lesson>{

  @Override
  public void insert(Lesson pLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`lesson`(`name`, `is_tp`, `is_test`, `begin_date`, `end_date`, `id_discipline`, `id_user_teacher`) VALUES (?, ?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pLesson.getName());
      stat.setBoolean(2, pLesson.isTp());
      stat.setBoolean(3, pLesson.isTest());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setDate(4, (java.sql.Date) new Date(), pLesson.getBeginHour());
      stat.setDate(5, (java.sql.Date) new Date(), pLesson.getEndHour());
      stat.setInt(6, 0); // pLesson.getDiscipline.getId()
      stat.setInt(7, pLesson.getTeacher().getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void update(Lesson pLesson) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`lesson` SET `name`=?,`is_tp`=?,`is_test`=?,`begin_date`=?,`end_date`=?,`id_discipline`=?,`id_user_teacher`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pLesson.getName());
      stat.setBoolean(2, pLesson.isTp());
      stat.setBoolean(3, pLesson.isTest());
      // pour la modification des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setDate(4, (java.sql.Date) new Date(), pLesson.getBeginHour());
      stat.setDate(5, (java.sql.Date) new Date(), pLesson.getEndHour());
      stat.setInt(6, 0); // pLesson.getDiscipline.getId()
      stat.setInt(7, pLesson.getTeacher().getId());
      
      stat.setInt(8, pLesson.getId());
      
      stat.executeUpdate();
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
  }

  @Override
  public void delete(Lesson objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Lesson selectById(int id) {
    Lesson lesson = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `lesson` WHERE  `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le status de la lesson
        /////////////////////////////////////////////////////////////////////////
        // C'est la discipline qui à un status
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le teacher de la lesson
        /////////////////////////////////////////////////////////////////////////
        // Teacher teacher = selectbyid res.getInt("id_user_teacher")
        // res.getDate("begin_date")
        // res.getDate("end_date")
        
        lesson = new Lesson(res.getInt("id"), res.getString("name"),
                            res.getBoolean("is_tp"), res.getBoolean("is_test"),
                            null, null,
                            null , null,
                            null ); // avant dernier arg education id education.. ?pb classe metier?
        
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return lesson;
  }

  @Override
  public List<Lesson> selectAll() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
