/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.ArrayList;
import java.util.List;
import metier.School;
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
public class SchoolServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private School        school;
  private SchoolService schoolService;
  
  public SchoolServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.school = new School("Ecole Insta");
    
    this.schoolService = new SchoolService(); // cette ligne juste pour les tests
    this.schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class SchoolService.
   */
  @Test
  public void testInsert() {
    boolean result = this.schoolService.insert(this.school) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class SchoolService.
   */
  @Test
  public void testUpdate() {
    this.school = this.schoolService.selectById(1);
    
    this.school.setName("Ecole Insta 2");
    boolean result = this.schoolService.update(this.school);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class SchoolService.
   */
  @Test
  public void testDelete() {
    this.school.setName("a_suppr");
    
    int id; // On récupère le dernier id généré
    id = this.schoolService.insert(this.school);
    
    // On re-récupère l'objet, pour le suppr
    this.school = this.schoolService.selectById(id);
    
    boolean result = this.schoolService.delete(this.school);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class SchoolService.
   */
  @Test
  public void testSelectById() {
    this.school = this.schoolService.selectById(1);
    assertTrue(this.school.getId() == 1);
  }

  /**
   * Test of selectAll method, of class SchoolService.
   */
  @Test
  public void testSelectAll() {
    List<School> listSchools = new ArrayList();
    listSchools = this.schoolService.selectAll();
    boolean result = listSchools.size() > 0;
    assertTrue(result);
  }
  
}
