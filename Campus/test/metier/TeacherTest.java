/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;
import java.util.List;
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
public class TeacherTest {
  
  private Teacher teacher;
  private Teacher teacher2;
  
  public TeacherTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.teacher = new Teacher("campus_teacher", "campus_teacher");
    this.teacher2 = new Teacher(this.teacher);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of updateLesson method, of class Teacher.
   */
  @Test
  public void testUpdateLesson() {
    System.out.println("updateLesson");
    Lesson lesson = null;
    Teacher instance = null;
    boolean expResult = false;
    boolean result = instance.updateLesson(lesson);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of uploadDoc method, of class Teacher.
   */
  @Test
  public void testUploadDoc() {
    System.out.println("uploadDoc");
    String path = "";
    Teacher instance = null;
    boolean expResult = false;
    boolean result = instance.uploadDoc(path);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of downloadDoc method, of class Teacher.
   */
  @Test
  public void testDownloadDoc() {
    System.out.println("downloadDoc");
    String path = "";
    Teacher instance = null;
    String expResult = "";
    String result = instance.downloadDoc(path);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
