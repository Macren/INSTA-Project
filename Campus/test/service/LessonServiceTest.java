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
  
  private School          school;
  private Teacher         teacher;
  private Discipline      discipline;
  private Lesson          lesson;
  private LessonService   lessonService;
  
  public LessonServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    this.school = schoolService.selectById(1);
    
    TeacherService teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
    this.teacher = teacherService.selectById(2);
    
    DisciplineService disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
    this.discipline = disciplineService.selectById(1);
    
    this.lesson = new Lesson("Premier cours JEE", false, false, 25, new Date(111), new Date(222), "Disponible", this.teacher, this.discipline);
    
    this.lessonService = new LessonService(); // cette ligne juste pour les tests
    this.lessonService = new LessonService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class LessonService.
   */
  @Test
  public void testInsert() {
    boolean result = this.lessonService.insert(this.lesson) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class LessonService.
   */
  @Test
  public void testUpdate() {
    this.lesson = this.lessonService.selectById(1);
    
    // Ne pas oublier de lui renseigner sa Discipline (sa metiere)
    this.lesson.setDiscipline(this.discipline);
    // et son Teacher
    this.lesson.setTeacher(this.teacher);
    
    this.lesson.setName("Premier cours JEE 2");
    this.lesson.setBeginDate(new Date(112));
    this.lesson.setEndDate(new Date(223));
    this.lesson.setStatus("Annulé");
    boolean result = this.lessonService.update(this.lesson);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class LessonService.
   */
  @Test
  public void testDelete() {
    this.lesson.setName("a_suppr");
    this.lesson.setBeginDate(new Date(999));
    this.lesson.setEndDate(new Date(800));
    this.lesson.setStatus("Annulé");
    
    int id; // On récupère le dernier id généré
    id = this.lessonService.insert(this.lesson);
    
    // On re-récupère l'objet, pour le suppr
    this.lesson = this.lessonService.selectById(id);
    
    boolean result = this.lessonService.delete(this.lesson);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class LessonService.
   */
  @Test
  public void testSelectById() {
    this.lesson = this.lessonService.selectById(1);
    assertTrue(this.lesson.getId() == 1);
  }

  /**
   * Test of selectAll method, of class LessonService.
   */
  @Test
  public void testSelectAll() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAll();
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByTeacherId method, of class LessonService.
   */
  @Test
  public void testSelectAllByTeacherId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAllByTeacherId(2);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAllByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllLessonsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllLessonsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAllLessonsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTpsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllTpsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAllTpsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTestsByDisciplineId method, of class LessonService.
   */
  @Test
  public void testSelectAllTestsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonService.selectAllTestsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }
  
}
