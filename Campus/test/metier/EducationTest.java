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
public class EducationTest {
  
  private School    school;
  private Education education;
  
  public EducationTest() {
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
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class Education.
   */
  @Test
  public void testGetId() {
    boolean result = this.education.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class Education.
   */
  @Test
  public void testSetId() {
    this.education.setId(2);
    boolean result = this.education.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of getNbHours method, of class Education.
   */
  @Test
  public void testGetNbHours() {
    boolean result = this.education.getNbHours() == 200;
    assertTrue(result);
  }

  /**
   * Test of setNbHours method, of class Education.
   */
  @Test
  public void testSetNbHours() {
    this.education.setNbHours(300);
    boolean result = this.education.getNbHours() == 300;
    assertTrue(result);
  }

  /**
   * Test of getPromo method, of class Education.
   */
  @Test
  public void testGetPromo() {
    boolean result = this.education.getPromo() == 11;
    assertTrue(result);
  }

  /**
   * Test of setPromo method, of class Education.
   */
  @Test
  public void testSetPromo() {
    this.education.setPromo(12);
    boolean result = this.education.getPromo() == 12;
    assertTrue(result);
  }

  /**
   * Test of getSchool method, of class Education.
   */
  @Test
  public void testGetSchool() {
    boolean result = this.education.getSchool().equals(this.school);
    assertTrue(result);
  }

  /**
   * Test of setSchool method, of class Education.
   */
  @Test
  public void testSetSchool() {
    School school2 = new School(2, "INSTA2");
    
    this.education.setSchool(school2);
    boolean result = this.education.getSchool().equals(school2);
    assertTrue(result);
  }

  /**
   * Test of getName method, of class Education.
   */
  @Test
  public void testGetName() {
    boolean result = this.education.getName().equals("Analyste Informaticien");
    assertTrue(result);
  }

  /**
   * Test of setName method, of class Education.
   */
  @Test
  public void testSetName() {
    this.education.setName("Analyste Informaticien2");
    boolean result = this.education.getName().equals("Analyste Informaticien2");
    assertTrue(result);
  }

  /**
   * Test of toString method, of class Education.
   */
  @Test
  public void testToString() {
    boolean result = this.education.toString().equals("Analyste Informaticien - Promo 11");
    assertTrue(result);
  }
  
}
