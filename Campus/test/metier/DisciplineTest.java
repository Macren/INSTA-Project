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
public class DisciplineTest {
  
  private School      school;
  private Education   education;
  private Discipline  discipline;
  
  public DisciplineTest() {
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
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class Discipline.
   */
  @Test
  public void testGetId() {
    boolean result = this.discipline.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class Discipline.
   */
  @Test
  public void testSetId() {
    this.discipline.setId(2);
    boolean result = this.discipline.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of getName method, of class Discipline.
   */
  @Test
  public void testGetName() {
    boolean result = this.discipline.getName().equals("Java");
    assertTrue(result);
  }

  /**
   * Test of setName method, of class Discipline.
   */
  @Test
  public void testSetName() {
    this.discipline.setName("Java EE");
    boolean result = this.discipline.getName().equals("Java EE");
    assertTrue(result);
  }

  /**
   * Test of getBeginDate method, of class Discipline.
   */
  @Test
  public void testGetBeginDate() {
    boolean result = this.discipline.getBeginDate().equals(new Date(101));
    assertTrue(result);
  }

  /**
   * Test of setBeginDate method, of class Discipline.
   */
  @Test
  public void testSetBeginDate() {
    this.discipline.setBeginDate(new Date(201));
    boolean result = this.discipline.getBeginDate().equals(new Date(201));
    assertTrue(result);
  }

  /**
   * Test of getEndDate method, of class Discipline.
   */
  @Test
  public void testGetEndDate() {
    boolean result = this.discipline.getEndDate().equals(new Date(102));
    assertTrue(result);
  }

  /**
   * Test of setEndDate method, of class Discipline.
   */
  @Test
  public void testSetEndDate() {
    this.discipline.setEndDate(new Date(202));
    boolean result = this.discipline.getEndDate().equals(new Date(202));
    assertTrue(result);
  }

  /**
   * Test of getEducation method, of class Discipline.
   */
  @Test
  public void testGetEducation() {
    boolean result = this.discipline.getEducation().equals(this.education);
    assertTrue(result);
  }

  /**
   * Test of setEducation method, of class Discipline.
   */
  @Test
  public void testSetEducation() {
    Education education2 = new Education(2, "Analyste Informaticien Reseau", 400, 12, this.school);
    
    this.discipline.setEducation(education2);
    boolean result = this.discipline.getEducation().equals(education2);
    assertTrue(result);
  }

  /**
   * Test of getStatus method, of class Discipline.
   */
  @Test
  public void testGetStatus() {
    boolean result = this.discipline.getStatus().equals("Disponible");
    assertTrue(result);
  }

  /**
   * Test of setStatus method, of class Discipline.
   */
  @Test
  public void testSetStatus() {
    this.discipline.setStatus("Complet");
    boolean result = this.discipline.getStatus().equals("Complet");
    assertTrue(result);
  }

  /**
   * Test of toString method, of class Discipline.
   */
  @Test
  public void testToString() {
    boolean result = this.discipline.toString().equals("Java");
    assertTrue(result);
  }
  
}
