/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
public class TeacherServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  
  private static Teacher        teacherTest;
  private static TeacherService teacherService;
  
  public TeacherServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    teacherService = new TeacherService(); // cette ligne juste pour le coverage
    teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    teacherTest = new Teacher("campus_teacher_test", "campus_teacher_test",
                              "campus_teacher_test@campus.com", new Date(0),
                              "campus_teacher_test", "campus_teacher_test",
                              0, "path/to/img/trombi_test",
                              schoolBdd, null); // dernier arg : Education
                              // null car un teacher n'a pas d'education( de formation)
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class TeacherService.
   */
  @Test
  public void testInsert() {
    int resultInt = teacherService.insert(teacherTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      teacherTest.setId(resultInt);
      teacherService.delete(teacherTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class TeacherService.
   */
  @Test
  public void testUpdate() {
    int resultInt = teacherService.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest.setId(resultInt);
      
      teacherTest.setLogin("campus_teacher_test2");
      teacherTest.setPasswd("campus_teacher_test2");
      teacherTest.setMail("campus_teacher_test2@campus.com");
      teacherTest.setFirstName("campus_teacher_test2");
      teacherTest.setLastName("campus_teacher_test2");
      teacherTest.setPathImgTrombi("path/to/img/trombi_test2");
      
      result = teacherService.update(teacherTest);
      
      teacherService.delete(teacherTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of delete method, of class TeacherService.
   */
  @Test
  public void testDelete() {
    int resultInt = teacherService.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest = teacherService.selectById(resultInt);
      
      result = teacherService.delete(teacherTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of selectById method, of class TeacherService.
   */
  @Test
  public void testSelectById() {
    teacherTest     = teacherService.selectById(2);
    boolean result  = teacherTest.getId() == 2;
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of selectByLoginPwd method, of class TeacherService.
   */
  @Test
  public void testSelectByLoginPwd() {
    int resultInt = teacherService.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest = teacherService.selectByLoginPwd("campus_teacher_test", "campus_teacher_test");
      
      result = teacherTest.getLogin().equals("campus_teacher_test")
                && teacherTest.getPasswd().equals("campus_teacher_test");
      
      teacherService.delete(teacherTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAll method, of class TeacherService.
   */
  @Test
  public void testSelectAll() {
    List<Teacher> listTeachers  = new ArrayList();
    listTeachers                = teacherService.selectAll();
    boolean result              = listTeachers.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllBySchoolId method, of class TeacherService.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Teacher> listTeachers  = new ArrayList();
    listTeachers                = this.teacherService.selectAllBySchoolId(1);
    boolean result              = listTeachers.size() > 0;
    assertTrue(result);
  }
  
}
