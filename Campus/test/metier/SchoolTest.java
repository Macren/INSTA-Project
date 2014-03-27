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
    School school = new School(1, "INSTA");
    
    boolean result = school.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class School.
   */
  @Test
  public void testSetId() {
    School school = new School("INSTA");
    
    school.setId(1);
    boolean result = school.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of getName method, of class School.
   */
  @Test
  public void testGetName() {
    School school = new School(1, "INSTA");
    
    boolean result = school.getName().equals("INSTA");
    assertTrue(result);
  }

  /**
   * Test of setName method, of class School.
   */
  @Test
  public void testSetName() {
    School school = new School("INSTA2");
    
    school.setName("INSTA");
    boolean result = school.getName().equals("INSTA");
    assertTrue(result);
  }

  /**
   * Test of toString method, of class School.
   */
  @Test
  public void testToString() {
    School school = new School(1, "INSTA");
    
    boolean result = school.toString().equals("INSTA");
    assertTrue(result);
  }
  
}
