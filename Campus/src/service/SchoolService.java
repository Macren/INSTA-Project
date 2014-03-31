/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.SchoolDAO;
import java.util.List;
import metier.School;

/**
 *
 * @author biron
 */
public class SchoolService {
  
  private final SchoolDAO schoolDao;
  
  public SchoolService(){
    this.schoolDao = new SchoolDAO();
  }
  
  public int insert(School pSchool) {
    return this.schoolDao.insert(pSchool);
  }
  
  public boolean update(School pSchool) {
    return this.schoolDao.update(pSchool);
  }
  
  public boolean delete(School pSchool) {
    return this.schoolDao.delete(pSchool);
  }
  
  public School selectById(int id) {
    return this.schoolDao.selectById(id);
  }
  
  public List<School> selectAll() {
    return this.schoolDao.selectAll();
  }
  
  
}
