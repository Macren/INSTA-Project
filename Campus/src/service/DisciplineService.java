/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.DisciplineDAO;
import java.util.List;
import metier.Discipline;
import metier.Education;

/**
 *
 * @author biron
 */
public class DisciplineService {
  
  private final DisciplineDAO disciplineDao;
  
  public DisciplineService(){
    this.disciplineDao = new DisciplineDAO();
  }
  
  public int insert(Discipline pDiscipline) {
    return this.disciplineDao.insert(pDiscipline);
  }
  
  public boolean update(Discipline pDiscipline) {
    return this.disciplineDao.update(pDiscipline);
  }
  
  public boolean delete(Discipline pDiscipline) {
    return this.disciplineDao.delete(pDiscipline);
  }
  
  public Discipline selectById(int id) {
    return this.disciplineDao.selectById(id);
  }
  
  public List<Discipline> selectAll() {
    return this.disciplineDao.selectAll();
  }
  
  public List<Discipline> selectAllByEducationId(int pEducationId) {
    return this.disciplineDao.selectAllByEducationId(pEducationId);
  }
  
  public List<Discipline> selectAllByEducationIdAndEducationPromo(Education pEducation) {
    return this.disciplineDao.selectAllByEducationIdAndEducationPromo(pEducation);
  }
  
  
  
}
