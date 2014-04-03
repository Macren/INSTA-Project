/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.List;
import metier.Discipline;
import metier.Mark;
import metier.School;
import metier.Student;
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
public class MarkDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Teacher     teacherBdd;
  private static Student     studentBdd;
  private static Discipline  disciplineBdd;
  
  private static Mark        markTest;
  private static MarkDAO     markDao;
  
  public MarkDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    TeacherDAO teacherDao = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    teacherBdd = teacherDao.selectById(2);
    
    StudentDAO studentDao = new StudentDAO(CONNECTION_STRING_BDD_TESTS);
    studentBdd = studentDao.selectById(3);
    
    DisciplineDAO disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
    disciplineBdd = disciplineDao.selectById(1);
    
    markDao = new MarkDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    markTest = new Mark((float) 10, (float) 20,
                        studentBdd, teacherBdd,
                        disciplineBdd, "note_test");
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class MarkDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = markDao.insert(markTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      markTest.setId(resultInt);
      markDao.delete(markTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  
  /**
   * Test of update method, of class MarkDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      markTest.setValue((float) 20);
      markTest.setComment("note_test2");
      
      result = markDao.update(markTest);
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class MarkDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest = markDao.selectById(resultInt);
      
      result = markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class MarkDAO.
   */
  @Test
  public void testSelectById() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      markTest  = markDao.selectById(resultInt);
      result    = markTest.getId() == resultInt;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class MarkDAO.
   */
  @Test
  public void testSelectAll() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = this.markDao.selectAll();
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByStudentId method, of class MarkDAO.
   */
  @Test
  public void testSelectAllByStudentId() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = markDao.selectAllByStudentId(studentBdd.getId());
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByTeacherId method, of class MarkDAO.
   */
  @Test
  public void testSelectAllByTeacherId() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = markDao.selectAllByTeacherId(teacherBdd.getId());
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByDisciplineId method, of class MarkDAO.
   */
  @Test
  public void testSelectAllByDisciplineId() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = markDao.selectAllByDisciplineId(disciplineBdd.getId());
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByDisciplineId method, of class MarkDAO.
   */
  @Test
  public void testSelectAllByStudentIdAndDisciplineId() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = markDao.selectAllByStudentIdAndDisciplineId(studentBdd.getId(), disciplineBdd.getId());
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  /**
   * Test of selectAllByTeacherIdAndDisciplineId method, of class MarkDAO.
   */
  @Test
  public void testSelectAllByTeacherIdAndDisciplineId() {
    int resultInt = markDao.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = markDao.selectAllByTeacherIdAndDisciplineId(teacherBdd.getId(), disciplineBdd.getId());
      result                = listMarks.size() > 0;
      
      markDao.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  
}
