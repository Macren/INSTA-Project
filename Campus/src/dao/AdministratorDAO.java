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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Administrator;

/**
 *
 * @author biron
 */
public class AdministratorDAO implements IDAO<Administrator>{

  @Override
  public void insert(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_promo`, `id_school`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      // pour l'insertion des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(4, pAdministrator.getBirthDate());
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      stat.setInt(8, 1);        // 1 : Administrator
      stat.setString(9, null);  // null, car un administrateur n'appartient à aucune promo
      stat.setInt(10, pAdministrator.getSchoolID());
      
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
  public void update(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "UPDATE `campus_bdd`.`user` SET `login`=?,`pwd`=?,`mail`=?,`birth_date`=?,`first_name`=?,`last_name`=?,`phone`=? WHERE `id`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      // pour la modification des dates, il faut les caster avant...
      // type de l'objet : Calendar
      // type en Bdd : DATETIME
      stat.setString(4, pAdministrator.getBirthDate());
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      
      stat.setInt(6, pAdministrator.getId());
      
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
  public void delete(Administrator objet) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Administrator selectById(int id) {
    
    Administrator administrator = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
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
        administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                          res.getString("password"), res.getString("mail"),
                                          res.getString("birth_date"), res.getString("first_name"),
                                          res.getString("last_name"), res.getInt("phone"),
                                          res.getInt("id_school"), 0);
      }
      
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(AdministratorDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return administrator;
    
  }

  @Override
  public List<Administrator> selectAll() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
