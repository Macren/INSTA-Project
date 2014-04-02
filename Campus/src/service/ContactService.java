/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.ContactDAO;
import java.util.List;
import metier.Contact;

/**
 *
 * @author biron
 */
public class ContactService {
  
  private final ContactDAO contactDao;
  
  /**
   * Ctor
   */
  public ContactService(){
    this.contactDao = new ContactDAO();
  }
  /**
   * Ctor for tests
   * @param pUrl 
   */
  public ContactService(String pUrl){
    this.contactDao = new ContactDAO(pUrl);
  }
  
  
  
  public int insert(Contact pContact) {
    return this.contactDao.insert(pContact);
  }
  
  public boolean update(Contact pContact) {
    return this.contactDao.update(pContact);
  }
  
  public boolean delete(Contact pContact) {
    return this.contactDao.delete(pContact);
  }
  
  // Ne marche pas
  public Contact selectById(int id) {
//    return this.contactDao.selectById(id);
    return null;
  }
  
  public Contact selectByUserIds(int pUserId1, int pUserId2) {
    return this.contactDao.selectByUserIds(pUserId1, pUserId2);
  }
  
  public List<Contact> selectAll() {
    return this.contactDao.selectAll();
  }
  
  public List<Contact> selectAllByOneUserId(int pUserId) {
    return this.contactDao.selectAllByOneUserId(pUserId);
  }
  
}
