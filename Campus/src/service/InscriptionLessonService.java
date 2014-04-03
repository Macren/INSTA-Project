/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.InscriptionLessonDAO;
import java.util.List;
import metier.InscriptionLesson;
import metier.Student;

/**
 *
 * @author Madeleine
 */
public class InscriptionLessonService {
    
  
  private final InscriptionLessonDAO signUpDao;
  
  /**
   * Ctor
   */
  public InscriptionLessonService(){
    this.signUpDao = new InscriptionLessonDAO();
  }
  /**
   * Ctor for tests
   * @param pUrl 
   */
//  public InscriptionLessonService(String pUrl){
//    this.signUpDao = new InscriptionLessonDAO(pUrl);
//  }
  
  public int insert(InscriptionLesson pSignUpLesson) {
    return this.signUpDao.insert(pSignUpLesson);
  }
  
  public boolean update(InscriptionLesson pSignUpLesson) {
    return this.signUpDao.update(pSignUpLesson);
  }
  
  public boolean delete(InscriptionLesson pSignUpLesson) {
    return this.signUpDao.delete(pSignUpLesson);
  }
  
  public List<Student> selectAllStudentByLessonId(int pLessonId) {
    return this.signUpDao.selectAllStudentsbyLessonId(pLessonId);
  }
}
