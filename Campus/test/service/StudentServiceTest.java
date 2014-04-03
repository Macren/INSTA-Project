/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import metier.Education;
import metier.School;
import metier.Student;
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
public class StudentServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Education   educationBdd;
  
  private static Student        studentTest;
  private static StudentService studentService;
  
  public StudentServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    EducationService educationService = new EducationService(CONNECTION_STRING_BDD_TESTS);
    educationBdd = educationService.selectById(1);
    
    studentService = new StudentService(); // cette ligne juste pour le coverage
    studentService = new StudentService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    studentTest = new Student("campus_student_test", "campus_student_test",
                              "campus_student_test@campus.com", new Date(0),
                              "campus_student_test", "campus_student_test",
                              0, "path/to/img/trombi_test",
                              schoolBdd, educationBdd);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class StudentService.
   */
  @Test
  public void testInsert() {
    int resultInt = studentService.insert(studentTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      studentTest.setId(resultInt);
      studentService.delete(studentTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class StudentService.
   */
  @Test
  public void testUpdate() {
    int resultInt = studentService.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest.setId(resultInt);
      
      studentTest.setLogin("campus_student_test2");
      studentTest.setPasswd("campus_student_test2");
      studentTest.setMail("campus_student_test2@campus.com");
      studentTest.setFirstName("campus_student_test2");
      studentTest.setLastName("campus_student_test2");
      studentTest.setPathImgTrombi("path/to/img/trombi_test2");
      
      result = studentService.update(studentTest);
      
      studentService.delete(studentTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of delete method, of class StudentService.
   */
  @Test
  public void testDelete() {
    int resultInt = studentService.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest = studentService.selectById(resultInt);
      
      result = studentService.delete(studentTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class StudentService.
   */
  @Test
  public void testSelectById() {
    studentTest     = studentService.selectById(3);
    boolean result  = studentTest.getId() == 3;
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of selectByLoginPwd method, of class StudentService.
   */
  @Test
  public void testSelectByLoginPwd() {
    int resultInt = studentService.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest = studentService.selectByLoginPwd("campus_student_test", "campus_student_test");
      
      result = studentTest.getLogin().equals("campus_student_test")
                && studentTest.getPasswd().equals("campus_student_test");
      
      studentService.delete(studentTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class StudentService.
   */
  @Test
  public void testSelectAll() {
    List<Student> listStudents  = new ArrayList();
    listStudents                = studentService.selectAll();
    boolean result              = listStudents.size() > 0;
    assertTrue(result);
  }
  
}
