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
  
  private School      school;
  private Teacher     teacher;
  private Discipline  discipline;
  private Lesson      lesson;
  private LessonDAO   lessonDao;
  
  public LessonDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    this.school = schoolDao.selectById(1);
    
    TeacherDAO teacherDao = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    this.teacher = teacherDao.selectById(2);
    
    DisciplineDAO disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
    this.discipline = disciplineDao.selectById(1);
    
    this.lesson = new Lesson("Premier cours JEE", false, false, 25, new Date(111), new Date(222), "Disponible", this.teacher, this.discipline);
    
    this.lessonDao = new LessonDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class LessonDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.lessonDao.insert(this.lesson) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class LessonDAO.
   */
  @Test
  public void testUpdate() {
    this.lesson = this.lessonDao.selectById(1);
    
    // Ne pas oublier de lui renseigner sa Discipline (sa metiere)
    this.lesson.setDiscipline(this.discipline);
    // et son Teacher
    this.lesson.setTeacher(this.teacher);
    
    this.lesson.setName("Premier cours JEE 2");
    this.lesson.setBeginDate(new Date(112));
    this.lesson.setEndDate(new Date(223));
    this.lesson.setStatus("Annulé");
    boolean result = this.lessonDao.update(this.lesson);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class LessonDAO.
   */
  @Test
  public void testDelete() {
    this.lesson.setName("a_suppr");
    this.lesson.setBeginDate(new Date(999));
    this.lesson.setEndDate(new Date(800));
    this.lesson.setStatus("Annulé");
    
    int id; // On récupère le dernier id généré
    id = this.lessonDao.insert(this.lesson);
    
    // On re-récupère l'objet, pour le suppr
    this.lesson = this.lessonDao.selectById(id);
    
    boolean result = this.lessonDao.delete(this.lesson);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class LessonDAO.
   */
  @Test
  public void testSelectById() {
    this.lesson = this.lessonDao.selectById(1);
    assertTrue(this.lesson.getId() == 1);
  }

  /**
   * Test of selectAll method, of class LessonDAO.
   */
  @Test
  public void testSelectAll() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAll();
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByTeacherId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllByTeacherId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAllByTeacherId(2);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAllByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllLessonsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllLessonsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAllLessonsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTpsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllTpsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAllTpsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllTestsByDisciplineId method, of class LessonDAO.
   */
  @Test
  public void testSelectAllTestsByDisciplineId() {
    List<Lesson> listLessons = new ArrayList();
    listLessons = this.lessonDao.selectAllTestsByDisciplineId(1);
    boolean result = listLessons.size() > 0;
    assertTrue(result);
  }
  
}
