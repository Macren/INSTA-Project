/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.TeacherDAO;
import java.util.List;
import metier.Teacher;

/**
 *
 * @author biron
 */
public class TeacherService {
  
  private final TeacherDAO teacherDao;
  
  public TeacherService(){
    this.teacherDao = new TeacherDAO();
  }
  
  public int insert(Teacher pTeacher) {
    return this.teacherDao.insert(pTeacher);
  }
  
  public boolean update(Teacher pTeacher) {
    return this.teacherDao.update(pTeacher);
  }
  
  public boolean delete(Teacher pTeacher) {
    return this.teacherDao.delete(pTeacher);
  }
  
  public Teacher selectById(int id) {
    return this.teacherDao.selectById(id);
  }
  
  public Teacher selectByLoginPwd(String pLogin, String pPwd) {
    return this.teacherDao.selectByLoginPwd(pLogin, pPwd);
  }
  
  public List<Teacher> selectAll() {
    return this.teacherDao.selectAll();
  }
  
  public List<Teacher> selectAllBySchoolId(int pSchoolId) {
    return this.teacherDao.selectAllBySchoolId(pSchoolId);
  }
  
  
  
}
