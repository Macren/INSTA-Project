/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import metier.Discipline;
import metier.Lesson;
import metier.School;
import metier.Teacher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author biron
 */
public class LessonDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Teacher     teacherBdd;
  private static Discipline  disciplineBdd;
  
  private static Lesson      lessonTest;
  private static LessonDAO   lessonDao;
  
  public LessonDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    TeacherDAO teacherDao = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    teacherBdd = teacherDao.selectById(2);
    
    DisciplineDAO disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
    disciplineBdd = disciplineDao.selectById(1);
    
    lessonDao = new LessonDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    lessonTest = new Lesson("cours_test", false,
                            false, 25,
                            new Date(000), new Date(000),
                            "Disponible", teacherBdd,
                            disciplineBdd);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class LessonDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = lessonDao.insert(lessonTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      lessonTest.setId(resultInt);
      lessonDao.delete(lessonTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class LessonDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = lessonDao.insert(lessonTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      lessonTest.setId(resultInt);
      
      lessonTest.setName("cours_test2");
      
      result = lessonDao.update(lessonTest);
      
      lessonDao.delete(lessonTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of delete method, of class LessonDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = lessonDao.insert(lessonTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      lessonTest = lessonDao.selectById(resultInt);
      
      result = lessonDao.delete(lessonTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class LessonDAO.
   */
  @Test
  public void testSelectById() {
    lessonTest = lessonDao.selectById(1);
    boolean result = lessonTest.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class LessonDAO.
   */
  @Test
  public void testSelectAll() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAll();
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByTeacherId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllByTeacherId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAllByTeacherId(2);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAllByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllLessonsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllLessonsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAllLessonsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllTpsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllTpsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAllTpsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTestsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllTestsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonDao.selectAllTestsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
}
