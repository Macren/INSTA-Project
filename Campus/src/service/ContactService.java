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
  
  
  /**
   * Créer un Contact en BDD
   * 
   * @param pContact
   * @return 
   */
  public int insert(Contact pContact) {
    return this.contactDao.insert(pContact);
  }
  
  /**
   * Récupère un Contact de la BDD en fonction des deux id d'utilisateurs
   * 
   * @param pUserId1
   * @param pUserId2
   * @return 
   */
  public Contact selectByUserIds(int pUserId1, int pUserId2) {
    return this.contactDao.selectByUserIds(pUserId1, pUserId2);
  }
  
  /**
   * Récupère une liste de Contacts de la BDD en fonction d'un id d'utilisateur
   * 
   * @param pUserId
   * @return 
   */
  public List<Contact> selectAllByOneUserId(int pUserId) {
    return this.contactDao.selectAllByOneUserId(pUserId);
  }
  
  /**
   * Supprime un Contact de la BDD
   * 
   * @param pContact
   * @return 
   */
  public boolean delete(Contact pContact) {
    return this.contactDao.delete(pContact);
  }
  
  
  
}
