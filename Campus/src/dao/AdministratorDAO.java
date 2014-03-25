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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Administrator;

/**
 *
 * @author biron
 */
public class AdministratorDAO implements IDAO<Administrator>{

  private static final int ID_ROLE_ADMINISTRATOR = 1;
  
  @Override
  public void insert(Administrator pAdministrator) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `campus_bdd`.`user` (`login`, `pwd`, `mail`, `birth_date`, `first_name`, `last_name`, `phone`, `id_role`, `id_promo`, `id_school`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      
      // create date like string with format
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String myDate = sdf.format(pAdministrator.getBirthDate().getTime());
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      stat.setString(4, myDate);
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      stat.setInt(8, ID_ROLE_ADMINISTRATOR);
      stat.setString(9, null);  // null, car un administrateur n'appartient à aucune promo
      stat.setInt(10, pAdministrator.getSchool().getId());
      
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
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String myDate = sdf.format(pAdministrator.getBirthDate().getTime());
      
      stat.setString(1, pAdministrator.getLogin());
      stat.setString(2, pAdministrator.getPasswd());
      stat.setString(3, pAdministrator.getMail());
      stat.setString(4, myDate);
      stat.setString(5, pAdministrator.getFirstName());
      stat.setString(6, pAdministrator.getLastName());
      stat.setInt(7, pAdministrator.getPhone());
      
      stat.setInt(8, pAdministrator.getId());
      
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
      String sql = "SELECT * FROM `campus_bdd`.`user` WHERE  `id` = ? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, id);
      stat.setInt(2, ID_ROLE_ADMINISTRATOR);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {          
          administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                            res.getString("pwd"), res.getString("mail"),
                                            res.getDate("birth_date"), res.getString("first_name"),
                                            res.getString("last_name"), res.getInt("phone"),
                                            null, null);
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
      /////////////////////////////////////////////////////////////////////////
      // Ici récupérer l'id de l'école en fonction 
      /////////////////////////////////////////////////////////////////////////
      String sql = "SELECT * FROM `campus_bdd`.`user` WHERE `login`=? AND `pwd`=? AND `id_role` = ?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setString(1, pLogin);
      stat.setString(2, pPwd);
      stat.setInt(3, ID_ROLE_ADMINISTRATOR);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {          
          administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                            res.getString("pwd"), res.getString("mail"),
                                            res.getDate("birth_date"), res.getString("first_name"),
                                            res.getString("last_name"), res.getInt("phone"),
                                            null, null);
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
          Administrator administrator = new Administrator(res.getInt("id"), res.getString("login"),
                                                        res.getString("pwd"), res.getString("mail"),
                                                        res.getDate("birth_date"), res.getString("first_name"),
                                                        res.getString("last_name"), res.getInt("phone"),
                                                        null, null);
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
  
}
