/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

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
public class LessonServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Teacher     teacherBdd;
  private static Discipline  disciplineBdd;
  
  private static Lesson         lessonTest;
  private static LessonService  lessonService;
  
  public LessonServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    TeacherService teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
    teacherBdd = teacherService.selectById(2);
    
    DisciplineService disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
    disciplineBdd = disciplineService.selectById(1);
    
    lessonService = new LessonService(); // cette ligne juste pour le coverage
    lessonService = new LessonService(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class LessonService.
   */
  @Test
  public void testInsert() {
    int resultInt = lessonService.insert(lessonTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      lessonTest.setId(resultInt);
      lessonService.delete(lessonTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class LessonService.
   */
  @Test
  public void testUpdate() {
    int resultInt = lessonService.insert(lessonTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      lessonTest.setId(resultInt);
      
      lessonTest.setName("cours_test2");
      
      result = lessonService.update(lessonTest);
      
      lessonService.delete(lessonTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class LessonService.
   */
  @Test
  public void testDelete() {
    int resultInt = lessonService.insert(lessonTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      lessonTest = lessonService.selectById(resultInt);
      
      result = lessonService.delete(lessonTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class LessonService.
   */
  @Test
  public void testSelectById() {
    lessonTest = lessonService.selectById(1);
    boolean result = lessonTest.getId() == 1;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class LessonService.
   */
  @Test
  public void testSelectAll() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAll();
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByTeacherId method, of class LessonService.
   */
  @Test
  public void testSelectAllByTeacherId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAllByTeacherId(2);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAllByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllLessonsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllLessonsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAllLessonsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllTpsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllTpsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAllTpsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTestsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllTestsByDisciplineId() {
    List<Lesson> listLessons  = new ArrayList();
    listLessons               = lessonService.selectAllTestsByDisciplineId(1);
    boolean result            = listLessons.size() > 0;
    assertTrue(result);
  }
  
}
