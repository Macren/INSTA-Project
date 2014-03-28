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

  @Override
  public boolean insert(Mark pMark) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`mark`(`value`, `value_max`, `id_user_student`, `id_user_teacher`, `id_discipline`, `comment`) VALUES (?,?,?,?,?,?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setFloat(1, pMark.getValue());
      stat.setFloat(2, pMark.getValueMax());
      stat.setInt(3, pMark.getStudent().getId());
      stat.setInt(4, pMark.getTeacher().getId());
      stat.setInt(5, pMark.getDiscipline().getId());
      stat.setString(6, pMark.getComment());
      
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
  public boolean update(Mark pMark) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`mark` SET `value`=?,`value_max`=?,`id_user_student`=?,`id_user_teacher`=?,`id_discipline`=?,`comment`=? WHERE `id`=?;";
      
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
  public boolean delete(Mark objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Mark selectById(int id) {
    Mark mark = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `campus_bdd`.`mark` WHERE `id` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le Student
        /////////////////////////////////////////////////////////////////////////
        Student student = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le Teacher
        /////////////////////////////////////////////////////////////////////////
        Teacher teacher = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la Discipline
        /////////////////////////////////////////////////////////////////////////
        Discipline discipline = null;
        
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

      String sql = "SELECT * FROM `campus_bdd`.`mark`;";
      Statement stat = cnx.createStatement();
      ResultSet res = stat.executeQuery(sql);
      
      while (res.next()) {
        
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le Student
        /////////////////////////////////////////////////////////////////////////
        Student student = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer le Teacher
        /////////////////////////////////////////////////////////////////////////
        Teacher teacher = null;
        /////////////////////////////////////////////////////////////////////////
        // Ici récupérer la Discipline
        /////////////////////////////////////////////////////////////////////////
        Discipline discipline = null;
        
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
