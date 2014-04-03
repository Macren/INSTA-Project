/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class StudentDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  private static Education   educationBdd;
  
  private static Student     studentTest;
  private static StudentDAO  studentDao;
  
  
  public StudentDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    EducationDAO educationDao = new EducationDAO(CONNECTION_STRING_BDD_TESTS);
    educationBdd = educationDao.selectById(1);
    
    studentDao = new StudentDAO(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class StudentDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = studentDao.insert(studentTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      studentTest.setId(resultInt);
      studentDao.delete(studentTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class StudentDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = studentDao.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest.setId(resultInt);
      
      studentTest.setLogin("campus_student_test2");
      studentTest.setPasswd("campus_student_test2");
      studentTest.setMail("campus_student_test2@campus.com");
      studentTest.setFirstName("campus_student_test2");
      studentTest.setLastName("campus_student_test2");
      studentTest.setPathImgTrombi("path/to/img/trombi_test2");
      
      result = studentDao.update(studentTest);
      
      studentDao.delete(studentTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of delete method, of class StudentDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = studentDao.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest = studentDao.selectById(resultInt);
      
      result = studentDao.delete(studentTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class StudentDAO.
   */
  @Test
  public void testSelectById() {
    studentTest     = studentDao.selectById(3);
    boolean result  = studentTest.getId() == 3;
    assertTrue(result);
  }
  
  
  /**
   * Test of selectByLoginPwd method, of class StudentDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    int resultInt = studentDao.insert(studentTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      studentTest = studentDao.selectByLoginPwd("campus_student_test", "campus_student_test");
      
      result = studentTest.getLogin().equals("campus_student_test")
                && studentTest.getPasswd().equals("campus_student_test");
      
      studentDao.delete(studentTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAll method, of class StudentDAO.
   */
  @Test
  public void testSelectAll() {
    List<Student> listStudents  = new ArrayList();
    listStudents                = studentDao.selectAll();
    boolean result              = listStudents.size() > 0;
    assertTrue(result);
  }
  
}
