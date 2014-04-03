/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class TeacherDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School      schoolBdd;
  
  private static Teacher     teacherTest;
  private static TeacherDAO  teacherDao;
  
  public TeacherDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    teacherDao = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class TeacherDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = teacherDao.insert(teacherTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      teacherTest.setId(resultInt);
      teacherDao.delete(teacherTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  /**
   * Test of update method, of class TeacherDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = teacherDao.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest.setId(resultInt);
      
      teacherTest.setLogin("campus_teacher_test2");
      teacherTest.setPasswd("campus_teacher_test2");
      teacherTest.setMail("campus_teacher_test2@campus.com");
      teacherTest.setFirstName("campus_teacher_test2");
      teacherTest.setLastName("campus_teacher_test2");
      teacherTest.setPathImgTrombi("path/to/img/trombi_test2");
      
      result = teacherDao.update(teacherTest);
      
      teacherDao.delete(teacherTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class TeacherDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = teacherDao.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest = teacherDao.selectById(resultInt);
      
      result = teacherDao.delete(teacherTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class TeacherDAO.
   */
  @Test
  public void testSelectById() {
    teacherTest     = teacherDao.selectById(2);
    boolean result  = teacherTest.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of selectByLoginPwd method, of class TeacherDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    int resultInt = teacherDao.insert(teacherTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      teacherTest = teacherDao.selectByLoginPwd("campus_teacher_test", "campus_teacher_test");
      
      result = teacherTest.getLogin().equals("campus_teacher_test")
                && teacherTest.getPasswd().equals("campus_teacher_test");
      
      teacherDao.delete(teacherTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAll method, of class TeacherDAO.
   */
  @Test
  public void testSelectAll() {
    List<Teacher> listTeachers  = new ArrayList();
    listTeachers                = teacherDao.selectAll();
    boolean result              = listTeachers.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllBySchoolId method, of class TeacherDAO.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Teacher> listTeachers  = new ArrayList();
    listTeachers                = this.teacherDao.selectAllBySchoolId(1);
    boolean result              = listTeachers.size() > 0;
    assertTrue(result);
  }
  
}
