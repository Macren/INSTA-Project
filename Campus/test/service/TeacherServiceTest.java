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
  
  private School          school;
  private Teacher         teacher;
  private TeacherService  teacherService;
  
  public TeacherServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    this.school = schoolService.selectById(1);
    
    this.teacher = new Teacher("campus_teacher", "campus_teacher",
                                "campus_teacher@campus.com", new Date(555),
                                "campus_teacher", "campus_teacher",
                                555, this.school, 
                                null); // dernier arg : Education
                                // null car un teacher n'a pas d'education( de formation)
    
    this.teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class TeacherService.
   */
  @Test
  public void testInsert() {
    boolean result = this.teacherService.insert(this.teacher) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class TeacherService.
   */
  @Test
  public void testUpdate() {
    this.teacher = this.teacherService.selectById(2);
    
    this.teacher.setLogin("campus_teacher2");
    this.teacher.setPasswd("campus_teacher2");
    this.teacher.setMail("campus_teacher2@campus.com");
    this.teacher.setBirthDate(new Date(444));
    this.teacher.setFirstName("campus_teacher2");
    this.teacher.setLastName("campus_teacher2");
    this.teacher.setPhone(444);
    boolean result = this.teacherService.update(this.teacher);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class TeacherService.
   */
  @Test
  public void testDelete() {
    this.teacher.setLogin("a_suppr");
    this.teacher.setPasswd("a_suppr");
    this.teacher.setMail("a_suppr@campus.com");
    this.teacher.setBirthDate(new Date(000));
    this.teacher.setFirstName("a_suppr");
    this.teacher.setLastName("a_suppr");
    this.teacher.setPhone(000);
    
    int id; // On récupère le dernier id généré
    id = this.teacherService.insert(this.teacher);
    
    // On re-récupère l'objet, pour le suppr
    this.teacher = this.teacherService.selectById(id);
    
    boolean result = this.teacherService.delete(this.teacher);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class TeacherService.
   */
  @Test
  public void testSelectById() {
    this.teacher = this.teacherService.selectById(2);
    assertTrue(this.teacher.getId() == 2);
  }

  /**
   * Test of selectByLoginPwd method, of class TeacherService.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.teacher = this.teacherService.selectByLoginPwd("campus_teacher2", "campus_teacher2");
    boolean result = this.teacher.getLogin().equals("campus_teacher2")
                      && this.teacher.getPasswd().equals("campus_teacher2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class TeacherService.
   */
  @Test
  public void testSelectAll() {
    List<Teacher> listTeachers = new ArrayList();
    listTeachers = this.teacherService.selectAll();
    boolean result = listTeachers.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllBySchoolId method, of class TeacherService.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Teacher> listTeachers = new ArrayList();
    listTeachers = this.teacherService.selectAllBySchoolId(1);
    
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
