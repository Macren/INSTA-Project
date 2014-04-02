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
  
  private School          school;
  private Education       education;
  private Student         student;
  private StudentService  studentService;
  
  public StudentServiceTest() {
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
    
    EducationService educationService = new EducationService(CONNECTION_STRING_BDD_TESTS);
    this.education = educationService.selectById(1);
    
    this.student = new Student("campus_student", "campus_student",
                              "campus_student@campus.com", new Date(888),
                              "campus_student", "campus_student",
                              888, "path/to/img/trombi", this.school,
                              this.education);
    
    this.studentService = new StudentService(); // cette ligne juste pour les tests
    this.studentService = new StudentService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class StudentService.
   */
  @Test
  public void testInsert() {
    boolean result = this.studentService.insert(this.student) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class StudentService.
   */
  @Test
  public void testUpdate() {
    this.student = this.studentService.selectById(3);
    
    // Ne pas oublier de lui renseigner son Education (sa formation)
    this.student.setEducation(this.education);
    
    this.student.setLogin("campus_student2");
    this.student.setPasswd("campus_student2");
    this.student.setMail("campus_student2@campus.com");
    this.student.setBirthDate(new Date(999));
    this.student.setFirstName("campus_student2");
    this.student.setLastName("campus_student2");
    this.student.setPhone(999);
    boolean result = this.studentService.update(this.student);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class StudentService.
   */
  @Test
  public void testDelete() {
    this.student.setLogin("a_suppr");
    this.student.setPasswd("a_suppr");
    this.student.setMail("a_suppr@campus.com");
    this.student.setBirthDate(new Date(000));
    this.student.setFirstName("a_suppr");
    this.student.setLastName("a_suppr");
    this.student.setPhone(000);
    
    int id; // On récupère le dernier id généré
    id = this.studentService.insert(this.student);
    
    // On re-récupère l'objet, pour le suppr
    this.student = this.studentService.selectById(id);
    
    boolean result = this.studentService.delete(this.student);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class StudentService.
   */
  @Test
  public void testSelectById() {
    this.student = this.studentService.selectById(3);
    assertTrue(this.student.getId() == 3);
  }

  /**
   * Test of selectByLoginPwd method, of class StudentService.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.student = this.studentService.selectByLoginPwd("campus_student2", "campus_student2");
    boolean result = this.student.getLogin().equals("campus_student2")
                      && this.student.getPasswd().equals("campus_student2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class StudentService.
   */
  @Test
  public void testSelectAll() {
    List<Student> listStudents = new ArrayList();
    listStudents = this.studentService.selectAll();
    boolean result = listStudents.size() > 0;
    assertTrue(result);
  }
  
}