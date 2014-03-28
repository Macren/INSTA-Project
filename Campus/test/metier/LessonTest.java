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
public class LessonTest {
  
  private School      school;
  private Education   education;
  private Discipline  discipline;
  private Teacher     teacher;
  private Lesson      lesson;
  
  public LessonTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.school     = new School(1, "INSTA");
    this.education  = new Education(1, "Analyste Informaticien", 200, 11, this.school);
    this.discipline = new Discipline(1, "Java", new Date(101), new Date(102), this.education, "Disponible");
    this.teacher    = new Teacher(1, "tea", "tea", "tea", new Date(555), "tea", "tea", 888, this.school, this.education);
    this.lesson     = new Lesson(1, "Cours Java 1", false, false, new Date(111), new Date(112), "Disponible", this.teacher, this.discipline);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class Lesson.
   */
  @Test
  public void testGetId() {
    boolean result = this.lesson.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class Lesson.
   */
  @Test
  public void testSetId() {
    this.lesson.setId(2);
    boolean result = this.lesson.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of getName method, of class Lesson.
   */
  @Test
  public void testGetName() {
    boolean result = this.lesson.getName().equals("Cours Java 1");
    assertTrue(result);
  }

  /**
   * Test of setName method, of class Lesson.
   */
  @Test
  public void testSetName() {
    this.lesson.setName("Cours Java 2");
    boolean result = this.lesson.getName().equals("Cours Java 2");
    assertTrue(result);
  }

  /**
   * Test of isTp method, of class Lesson.
   */
  @Test
  public void testIsTp() {
    boolean result = this.lesson.isTp() == false;
    assertTrue(result);
  }

  /**
   * Test of setTp method, of class Lesson.
   */
  @Test
  public void testSetTp() {
    this.lesson.setTp(true);
    boolean result = this.lesson.isTp() == true;
    assertTrue(result);
  }

  /**
   * Test of isTest method, of class Lesson.
   */
  @Test
  public void testIsTest() {
    boolean result = this.lesson.isTest()== false;
    assertTrue(result);
  }

  /**
   * Test of setTest method, of class Lesson.
   */
  @Test
  public void testSetTest() {
    this.lesson.setTest(true);
    boolean result = this.lesson.isTest()== true;
    assertTrue(result);
  }

  /**
   * Test of getBeginDate method, of class Lesson.
   */
  @Test
  public void testGetBeginDate() {
    boolean result = this.lesson.getBeginDate().equals(new Date(111));
    assertTrue(result);
  }

  /**
   * Test of setBeginDate method, of class Lesson.
   */
  @Test
  public void testSetBeginDate() {
    this.lesson.setBeginDate(new Date(211));
    boolean result = this.lesson.getBeginDate().equals(new Date(211));
    assertTrue(result);
  }

  /**
   * Test of getEndDate method, of class Lesson.
   */
  @Test
  public void testGetEndDate() {
    boolean result = this.lesson.getEndDate().equals(new Date(112));
    assertTrue(result);
  }

  /**
   * Test of setEndDate method, of class Lesson.
   */
  @Test
  public void testSetEndDate() {
    this.lesson.setEndDate(new Date(212));
    boolean result = this.lesson.getEndDate().equals(new Date(212));
    assertTrue(result);
  }

  /**
   * Test of getStatus method, of class Lesson.
   */
  @Test
  public void testGetStatus() {
    boolean result = this.lesson.getStatus().equals("Disponible");
    assertTrue(result);
  }

  /**
   * Test of setStatus method, of class Lesson.
   */
  @Test
  public void testSetStatus() {
    this.lesson.setStatus("Terminé");
    boolean result = this.lesson.getStatus().equals("Terminé");
    assertTrue(result);
  }

  /**
   * Test of getTeacher method, of class Lesson.
   */
  @Test
  public void testGetTeacher() {
    boolean result = this.lesson.getTeacher().equals(this.teacher);
    assertTrue(result);
  }

  /**
   * Test of setTeacher method, of class Lesson.
   */
  @Test
  public void testSetTeacher() {
    Teacher teacher2 = new Teacher(2, "2", "2", "2", new Date(555), "2", "2", 777, this.school, this.education);
    
    this.lesson.setTeacher(teacher2);
    boolean result = this.lesson.getTeacher().equals(teacher2);
    assertTrue(result);
  }

  /**
   * Test of getDiscipline method, of class Lesson.
   */
  @Test
  public void testGetDiscipline() {
    System.out.println("getDiscipline");
    Lesson instance = null;
    Discipline expResult = null;
    Discipline result = instance.getDiscipline();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setDiscipline method, of class Lesson.
   */
  @Test
  public void testSetDiscipline() {
    System.out.println("setDiscipline");
    Discipline discipline = null;
    Lesson instance = null;
    instance.setDiscipline(discipline);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Lesson.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Lesson instance = null;
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
