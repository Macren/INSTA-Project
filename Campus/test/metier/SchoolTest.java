/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

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
public class SchoolTest {
  
  public SchoolTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class School.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");
    School instance = null;
    int expResult = 0;
    int result = instance.getId();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setId method, of class School.
   */
  @Test
  public void testSetId() {
    System.out.println("setId");
    int id = 0;
    School instance = null;
    instance.setId(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getName method, of class School.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    School instance = null;
    String expResult = "";
    String result = instance.getName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setName method, of class School.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    School instance = null;
    instance.setName(name);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toString method, of class School.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    School instance = null;
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
