/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.AdministratorDAO;
import java.util.List;
import metier.Administrator;

/**
 *
 * @author biron
 */
public class AdministratorService {
  
  private final AdministratorDAO administratorDao;
  
  /**
   * Ctor
   */
  public AdministratorService(){
    this.administratorDao = new AdministratorDAO();
  }
  /**
   * Ctor for tests
   * @param pUrl 
   */
  public AdministratorService(String pUrl){
    this.administratorDao = new AdministratorDAO(pUrl);
  }
  
  
  public int insert(Administrator pAdministrator) {
    return this.administratorDao.insert(pAdministrator);
  }
  
  public boolean update(Administrator pAdministrator) {
    return this.administratorDao.update(pAdministrator);
  }
  
  public boolean delete(Administrator pAdministrator) {
    return this.administratorDao.delete(pAdministrator);
  }
  
  public Administrator selectById(int id) {
    return this.administratorDao.selectById(id);
  }
  
  public Administrator selectByLoginPwd(String pLogin, String pPwd) {
    return this.administratorDao.selectByLoginPwd(pLogin, pPwd);
  }
  
  public List<Administrator> selectAll() {
    return this.administratorDao.selectAll();
  }
  
  public List<Administrator> selectAllBySchoolId(int pSchoolId) {
    return this.administratorDao.selectAllBySchoolId(pSchoolId);
  }
  
  
  
}
