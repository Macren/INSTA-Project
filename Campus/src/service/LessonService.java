/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import dao.LessonDAO;
import java.util.List;
import metier.Lesson;

/**
 *
 * @author biron
 */
public class LessonService {
  
  private final LessonDAO lessonDao;
  
  /**
   * Ctor
   */
  public LessonService(){
    this.lessonDao = new LessonDAO();
  }
  /**
   * Ctor for tests
   * @param pUrl 
   */
  public LessonService(String pUrl){
    this.lessonDao = new LessonDAO(pUrl);
  }
  
  public int insert(Lesson pLesson) {
    return this.lessonDao.insert(pLesson);
  }
  
  public boolean update(Lesson pLesson) {
    return this.lessonDao.update(pLesson);
  }
  
  public boolean delete(Lesson pLesson) {
    return this.lessonDao.delete(pLesson);
  }
  
  public Lesson selectById(int id) {
    return this.lessonDao.selectById(id);
  }
  
  public List<Lesson> selectAll() {
    return this.lessonDao.selectAll();
  }
  
  public List<Lesson> selectAllByTeacherId(int pTeacherId) {
    return this.lessonDao.selectAllByTeacherId(pTeacherId);
  }
  
  public List<Lesson> selectAllByDisciplineId(int pDisciplineId) {
    return this.lessonDao.selectAllByDisciplineId(pDisciplineId);
  }
  
  public List<Lesson> selectAllLessonsByDisciplineId(int pDisciplineId) {
    return this.lessonDao.selectAllLessonsByDisciplineId(pDisciplineId);
  }
  
  public List<Lesson> selectAllTpsByDisciplineId(int pDisciplineId) {
    return this.lessonDao.selectAllTpsByDisciplineId(pDisciplineId);
  }
  
  public List<Lesson> selectAllTestsByDisciplineId(int pDisciplineId) {
    return this.lessonDao.selectAllTestsByDisciplineId(pDisciplineId);
  }
  
  
  
}
