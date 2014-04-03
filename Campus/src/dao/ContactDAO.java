/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.IDAO.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.AbstractUser;
import metier.Administrator;
import metier.Contact;

/**
 *
 * @author Thierry
 */
public class ContactDAO {
  
  public DB db = new DB();
  
  
  public ContactDAO() {
  }
  
  public ContactDAO(String pUrl) {
    this.db.setUrl(pUrl);
  }
  
  
  
  
  public int insert(Contact pContact) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "INSERT INTO `contact`(`id_user_1`,`id_user_2`) VALUES (?,?);";
      
      // on verifie si la ligne existe déja en BDD
      if(this.selectByUserIds(pContact.getUtilisateur1().getId(), pContact.getUtilisateur2().getId()) == null){
        PreparedStatement stat = cnx.prepareStatement(sql);
        stat.setInt(1, pContact.getUtilisateur1().getId());
        stat.setInt(2, pContact.getUtilisateur2().getId());
        stat.executeUpdate();
      }
      
      // on verifie si la ligne existe déja en BDD
      if(this.selectByUserIds(pContact.getUtilisateur2().getId(), pContact.getUtilisateur1().getId()) == null){
        PreparedStatement statBis = cnx.prepareStatement(sql);
        statBis.setInt(1, pContact.getUtilisateur2().getId());
        statBis.setInt(2, pContact.getUtilisateur1().getId());
        statBis.executeUpdate();
      }
      
      return 1;
      
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    
    return 0;
  }
  
  
  
  
  
  public Contact selectByUserIds(int pUserId1, int pUserId2) {
    Contact contact = null;
    
    Connection cnx = null;
    
    try {
      cnx= db.connect();
      
      String sql = "SELECT * FROM `contact` WHERE `id_user_1`=? AND `id_user_2`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pUserId1);
      stat.setInt(2, pUserId2);
      ResultSet res = stat.executeQuery();
      
      // S'il y a un resultat
      if(res.first())
      {
        // On récup les utilisateurs
        AbstractUser u1 = null;
        AbstractUser u2 = null;
        
        AdministratorDAO administratorDao = new AdministratorDAO();
        TeacherDAO teacherDao             = new TeacherDAO();
        StudentDAO studentDao             = new StudentDAO();
        
        if(administratorDao.selectById(res.getInt("id_user_1")) != null){
          u1 = administratorDao.selectById(res.getInt("id_user_1"));
        }
        if(teacherDao.selectById(res.getInt("id_user_1")) != null){
          u1 = teacherDao.selectById(res.getInt("id_user_1"));
        }
        if(studentDao.selectById(res.getInt("id_user_1")) != null){
          u1 = studentDao.selectById(res.getInt("id_user_1"));
        }
        
        if(administratorDao.selectById(res.getInt("id_user_2")) != null){
          u2 = administratorDao.selectById(res.getInt("id_user_2"));
        }
        if(teacherDao.selectById(res.getInt("id_user_2")) != null){
          u2 = teacherDao.selectById(res.getInt("id_user_2"));
        }
        if(studentDao.selectById(res.getInt("id_user_2")) != null){
          u2 = studentDao.selectById(res.getInt("id_user_2"));
        }
        
        contact = new Contact(u1, u2);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return contact;
  }
  
  
  
  
  
  
  public List<Contact> selectAllByOneUserId(int pUserId) {
    List<Contact> listContacts = new ArrayList();
    
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "SELECT * FROM `contact` WHERE `id_user_1`=?;";
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pUserId);
      ResultSet res = stat.executeQuery();
      
      while (res.next()) {
        
        // ici récup les utilisateurs
        AbstractUser u1 = null;
        AbstractUser u2 = null;
        
        AdministratorDAO administratorDao = new AdministratorDAO();
        TeacherDAO teacherDao             = new TeacherDAO();
        StudentDAO studentDao             = new StudentDAO();
        
        if(administratorDao.selectById(pUserId) != null){
          u1 = administratorDao.selectById(pUserId);
        }
        if(teacherDao.selectById(pUserId) != null){
          u1 = teacherDao.selectById(pUserId);
        }
        if(studentDao.selectById(pUserId) != null){
          u1 = studentDao.selectById(pUserId);
        }
        
        if(administratorDao.selectById(res.getInt("id_user_2")) != null){
          u2 = administratorDao.selectById(res.getInt("id_user_2"));
        }
        if(teacherDao.selectById(res.getInt("id_user_2")) != null){
          u2 = teacherDao.selectById(res.getInt("id_user_2"));
        }
        if(studentDao.selectById(res.getInt("id_user_2")) != null){
          u2 = studentDao.selectById(res.getInt("id_user_2"));
        }
        
        Contact contact = new Contact(u1, u2);
        
        listContacts.add(contact);
      }
      
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        db.disconnect(cnx);
    }

    return listContacts;
  }
  
  
  
  
  
  
  
  public boolean delete(Contact pContact) {
    Connection cnx = null;
    
    try {
      cnx = db.connect();
      
      String sql = "DELETE FROM `contact` WHERE `id_user_1`=? AND `id_user_2`=?;";
      
      PreparedStatement stat = cnx.prepareStatement(sql);
      stat.setInt(1, pContact.getUtilisateur1().getId());
      stat.setInt(2, pContact.getUtilisateur2().getId());
      stat.executeUpdate();
      
      PreparedStatement statBis = cnx.prepareStatement(sql);
      statBis.setInt(1, pContact.getUtilisateur2().getId());
      statBis.setInt(2, pContact.getUtilisateur1().getId());
      statBis.executeUpdate();
      
      return true;
      
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      db.disconnect(cnx);
    }
    return false;
  }
  
  
  
  
  

}
