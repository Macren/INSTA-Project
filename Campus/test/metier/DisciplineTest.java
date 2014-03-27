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
    this.school = new School(1, "INSTA");
    this.education = new Education(1, "Analyste Informaticien", 200, 11, this.school);
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
    System.out.println("getId");
    Discipline instance = null;
    int expResult = 0;
    int result = instance.getId();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setId method, of class Discipline.
   */
  @Test
  public void testSetId() {
    System.out.println("setId");
    int id = 0;
    Discipline instance = null;
    instance.setId(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getName method, of class Discipline.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    Discipline instance = null;
    String expResult = "";
    String result = instance.getName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setName method, of class Discipline.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    Discipline instance = null;
    instance.setName(name);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBeginDate method, of class Discipline.
   */
  @Test
  public void testGetBeginDate() {
    System.out.println("getBeginDate");
    Discipline instance = null;
    Date expResult = null;
    Date result = instance.getBeginDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBeginDate method, of class Discipline.
   */
  @Test
  public void testSetBeginDate() {
    System.out.println("setBeginDate");
    Date beginDate = null;
    Discipline instance = null;
    instance.setBeginDate(beginDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEndDate method, of class Discipline.
   */
  @Test
  public void testGetEndDate() {
    System.out.println("getEndDate");
    Discipline instance = null;
    Date expResult = null;
    Date result = instance.getEndDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setEndDate method, of class Discipline.
   */
  @Test
  public void testSetEndDate() {
    System.out.println("setEndDate");
    Date endDate = null;
    Discipline instance = null;
    instance.setEndDate(endDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEducation method, of class Discipline.
   */
  @Test
  public void testGetEducation() {
    System.out.println("getEducation");
    Discipline instance = null;
    Education expResult = null;
    Education result = instance.getEducation();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setEducation method, of class Discipline.
   */
  @Test
  public void testSetEducation() {
    System.out.println("setEducation");
    Education education = null;
    Discipline instance = null;
    instance.setEducation(education);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getStatus method, of class Discipline.
   */
  @Test
  public void testGetStatus() {
    System.out.println("getStatus");
    Discipline instance = null;
    String expResult = "";
    String result = instance.getStatus();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setStatus method, of class Discipline.
   */
  @Test
  public void testSetStatus() {
    System.out.println("setStatus");
    String status = "";
    Discipline instance = null;
    instance.setStatus(status);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class Discipline.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    Discipline instance = null;
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
