/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.EducationDAO;
import java.util.List;
import metier.Education;

/**
 *
 * @author biron
 */
public class EducationService {
  
  private final EducationDAO educationDao;
  
  public EducationService(){
    this.educationDao = new EducationDAO();
  }
  
  public int insert(Education pEducation) {
    return this.educationDao.insert(pEducation);
  }
  
  public boolean update(Education pEducation) {
    return this.educationDao.update(pEducation);
  }
  
  public boolean delete(Education pEducation) {
    return this.educationDao.delete(pEducation);
  }
  
  public Education selectById(int id) {
    return this.educationDao.selectById(id);
  }
  
  public List<Education> selectAll() {
    return this.educationDao.selectAll();
  }
  
  public List<Education> selectAllBySchoolId(int pSchoolId) {
    return this.educationDao.selectAllBySchoolId(pSchoolId);
  }
  
  
  
}
