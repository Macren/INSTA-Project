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
  
  private School      school;
  private Teacher     teacher;
  private TeacherDAO  teacherDao;
  
  public TeacherDAOTest() {
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
    
    this.teacher = new Teacher("campus_teacher", "campus_teacher",
                                "campus_teacher@campus.com", new Date(555),
                                "campus_teacher", "campus_teacher",
                                555, this.school, 
                                null); // dernier arg : Education
                                // null car un teacher n'a pas d'education( de formation)
    
    this.teacherDao = new TeacherDAO("jdbc:mysql://localhost/campus_bdd_tests");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class TeacherDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.teacherDao.insert(this.teacher);;
    assertTrue(result);
  }

  /**
   * Test of update method, of class TeacherDAO.
   */
  @Test
  public void testUpdate() {
    this.teacher = this.teacherDao.selectById(2);
    
    this.teacher.setLogin("campus_teacher2");
    this.teacher.setPasswd("campus_teacher2");
    this.teacher.setMail("campus_teacher2@campus.com");
    this.teacher.setBirthDate(new Date(444));
    this.teacher.setFirstName("campus_teacher2");
    this.teacher.setLastName("campus_teacher2");
    this.teacher.setPhone(444);
    boolean result = this.teacherDao.update(this.teacher);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class TeacherDAO.
   */
  @Test
  public void testDelete() {
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectById method, of class TeacherDAO.
   */
  @Test
  public void testSelectById() {
    this.teacher = this.teacherDao.selectById(2);
    assertTrue(this.teacher.getId() == 2);
  }

  /**
   * Test of selectByLoginPwd method, of class TeacherDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.teacher = this.teacherDao.selectByLoginPwd("campus_teacher2", "campus_teacher2");
    boolean result = this.teacher.getLogin().equals("campus_teacher2")
                      && this.teacher.getPasswd().equals("campus_teacher2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class TeacherDAO.
   */
  @Test
  public void testSelectAll() {
    List<Teacher> listTeachers = new ArrayList();
    listTeachers = this.teacherDao.selectAll();
    boolean result = listTeachers.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllBySchoolId method, of class TeacherDAO.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Teacher> listTeachers = new ArrayList();
    listTeachers = this.teacherDao.selectAllBySchoolId(1);
    
    boolean resultBis = true;
    for (Teacher t : listTeachers) {
      if(t.getSchool().getId() != 1)
      {
        resultBis = false;
      }
    }
    
    boolean result = resultBis && listTeachers.size() > 0;
    assertTrue(result);
  }
  
}
