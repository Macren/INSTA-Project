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
    
    this.student = new Student(3, "campus_student", "campus_student",
                              "campus_student@campus.com", new Date(888),
                              "campus_student", "campus_student",
                              888, this.school,
                              this.education);
    
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
    boolean result = this.studentDao.insert(this.student);
    assertTrue(result);
  }

  /**
   * Test of update method, of class StudentDAO.
   */
  @Test
  public void testUpdate() {
    this.student = this.studentDao.selectById(3);
    
    this.student.setLogin("campus_student2");
    this.student.setPasswd("campus_student2");
    this.student.setMail("campus_student2@campus.com");
    this.student.setBirthDate(new Date(999));
    this.student.setFirstName("campus_student2");
    this.student.setLastName("campus_student2");
    this.student.setPhone(999);
    boolean result = this.studentDao.update(this.student);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class StudentDAO.
   */
  @Test
  public void testDelete() {
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectById method, of class StudentDAO.
   */
  @Test
  public void testSelectById() {
    this.student = this.studentDao.selectById(3);
    assertTrue(this.student.getId() == 3);
  }

  /**
   * Test of selectByLoginPwd method, of class StudentDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.student = this.studentDao.selectByLoginPwd("campus_student2", "campus_student2");
    boolean result = this.student.getLogin().equals("campus_student2")
                      && this.student.getPasswd().equals("campus_student2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class StudentDAO.
   */
  @Test
  public void testSelectAll() {
    List<Student> listStudents = new ArrayList();
    listStudents = this.studentDao.selectAll();
    boolean result = listStudents.size() > 0;
    assertTrue(result);
  }
  
}