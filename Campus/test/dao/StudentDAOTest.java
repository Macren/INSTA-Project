/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Date;
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
  
  private School      school;
  private Education   education;
  private Student     student;
  private StudentDAO  studentDao;
  
  public StudentDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolDAO schoolDao = new SchoolDAO("jdbc:mysql://localhost/campus_bdd_tests");
    this.school = schoolDao.selectById(1);
    
    this.education = new Education(1, "Analyste Informaticien", 200, 11, this.school);
    
    this.student = new Student(3, "campus_student",
                                  "campus_student", "campus_student@campus.com",
                                  new Date(888), "campus_student",
                                  "campus_student", 888,
                                  this.school, this.education);
    
    this.studentDao = new StudentDAO("jdbc:mysql://localhost/campus_bdd_tests");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class StudentDAO.
   */
  @Test
  public void testInsert() {
    System.out.println("insert");
    Student pStudent = null;
    StudentDAO instance = new StudentDAO();
    boolean expResult = false;
    boolean result = instance.insert(pStudent);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of update method, of class StudentDAO.
   */
  @Test
  public void testUpdate() {
    System.out.println("update");
    Student pStudent = null;
    StudentDAO instance = new StudentDAO();
    boolean expResult = false;
    boolean result = instance.update(pStudent);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of delete method, of class StudentDAO.
   */
  @Test
  public void testDelete() {
    System.out.println("delete");
    Student objet = null;
    StudentDAO instance = new StudentDAO();
    boolean expResult = false;
    boolean result = instance.delete(objet);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectById method, of class StudentDAO.
   */
  @Test
  public void testSelectById() {
    System.out.println("selectById");
    int id = 0;
    StudentDAO instance = new StudentDAO();
    Student expResult = null;
    Student result = instance.selectById(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectByLoginPwd method, of class StudentDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    System.out.println("selectByLoginPwd");
    String pLogin = "";
    String pPwd = "";
    StudentDAO instance = new StudentDAO();
    Student expResult = null;
    Student result = instance.selectByLoginPwd(pLogin, pPwd);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectAll method, of class StudentDAO.
   */
  @Test
  public void testSelectAll() {
    System.out.println("selectAll");
    StudentDAO instance = new StudentDAO();
    List<Student> expResult = null;
    List<Student> result = instance.selectAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
