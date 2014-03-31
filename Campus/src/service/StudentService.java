/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.StudentDAO;
import java.util.List;
import metier.Student;

/**
 *
 * @author biron
 */
public class StudentService {
  
  private final StudentDAO studentDao;
  
  public StudentService(){
    this.studentDao = new StudentDAO();
  }
  
  public int insert(Student pStudent) {
    return this.studentDao.insert(pStudent);
  }
  
  public boolean update(Student pStudent) {
    return this.studentDao.update(pStudent);
  }
  
  public boolean delete(Student pStudent) {
    return this.studentDao.delete(pStudent);
  }
  
  public Student selectById(int id) {
    return this.studentDao.selectById(id);
  }
  
  public Student selectByLoginPwd(String pLogin, String pPwd) {
    return this.studentDao.selectByLoginPwd(pLogin, pPwd);
  }
  
  public List<Student> selectAll() {
    return this.studentDao.selectAll();
  }
  
  
  
}
