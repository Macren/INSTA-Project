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
  
  private School school;
  
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
    this.school = new School(1, "INSTA");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class School.
   */
  @Test
  public void testGetId() {
    boolean result = this.school.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class School.
   */
  @Test
  public void testSetId() {
    this.school.setId(2);
    boolean result = this.school.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of getName method, of class School.
   */
  @Test
  public void testGetName() {
    boolean result = this.school.getName().equals("INSTA");
    assertTrue(result);
  }

  /**
   * Test of setName method, of class School.
   */
  @Test
  public void testSetName() {
    this.school.setName("INSTA2");
    boolean result = this.school.getName().equals("INSTA2");
    assertTrue(result);
  }

  /**
   * Test of toString method, of class School.
   */
  @Test
  public void testToString() {
    boolean result = this.school.toString().equals("INSTA");
    assertTrue(result);
  }
  
}
