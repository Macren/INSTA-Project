/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;
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
public class StudentTest {
  
  private Student student;
  private Student student2;
  
  public StudentTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.student = new Student("campus_student", "campus_student");
    this.student2 = new Student(this.student);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of signUpLesson method, of class Student.
   */
  @Test
  public void testSignUpLesson() {
    System.out.println("signUpLesson");
    Lesson lesson = null;
    Student instance = null;
    boolean expResult = false;
    boolean result = instance.signUpLesson(lesson);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of signOutLesson method, of class Student.
   */
  @Test
  public void testSignOutLesson() {
    System.out.println("signOutLesson");
    Lesson lesson = null;
    Student instance = null;
    boolean expResult = false;
    boolean result = instance.signOutLesson(lesson);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of uploadDoc method, of class Student.
   */
  @Test
  public void testUploadDoc() {
    System.out.println("uploadDoc");
    String path = "";
    Student instance = null;
    boolean expResult = false;
    boolean result = instance.uploadDoc(path);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of downloadDoc method, of class Student.
   */
  @Test
  public void testDownloadDoc() {
    System.out.println("downloadDoc");
    String path = "";
    Student instance = null;
    String expResult = "";
    String result = instance.downloadDoc(path);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
