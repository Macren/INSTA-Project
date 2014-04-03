/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

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
public class MarkServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Teacher     teacherBdd;
  private static Student     studentBdd;
  private static Discipline  disciplineBdd;
  
  private static Mark         markTest;
  private static MarkService  markService;
  
  public MarkServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    TeacherService teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
    teacherBdd = teacherService.selectById(2);
    
    StudentService studentService = new StudentService(CONNECTION_STRING_BDD_TESTS);
    studentBdd = studentService.selectById(3);
    
    DisciplineService disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
    disciplineBdd = disciplineService.selectById(1);
    
    markService = new MarkService(); // cette ligne juste pour le coverage
    markService = new MarkService(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class MarkService.
   */
  @Test
  public void testInsert() {
    int resultInt = markService.insert(markTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      markTest.setId(resultInt);
      markService.delete(markTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class MarkService.
   */
  @Test
  public void testUpdate() {
    int resultInt = markService.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      markTest.setValue((float) 20);
      markTest.setComment("note_test2");
      
      result = markService.update(markTest);
      
      markService.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class MarkService.
   */
  @Test
  public void testDelete() {
    int resultInt = markService.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest = markService.selectById(resultInt);
      
      result = markService.delete(markTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class MarkService.
   */
  @Test
  public void testSelectById() {
    int resultInt = markService.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      markTest  = markService.selectById(resultInt);
      result    = markTest.getId() == resultInt;
      
      markService.delete(markTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class MarkService.
   */
  @Test
  public void testSelectAll() {
    int resultInt = markService.insert(markTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      markTest.setId(resultInt);
      
      List<Mark> listMarks  = new ArrayList();
      listMarks             = this.markService.selectAll();
      result                = listMarks.size() > 0;
      
      markService.delete(markTest);
    }
    
    assertTrue(result);
  }
  
}
