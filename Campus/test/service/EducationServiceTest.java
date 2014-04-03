/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.ArrayList;
import java.util.List;
import metier.Education;
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
public class EducationServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School           schoolBdd;
  private static Education        educationTest;
  private static EducationService educationService;
  
  public EducationServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    educationService = new EducationService(); // cette ligne juste pour le coverage
    educationService = new EducationService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    educationTest = new Education("formation_test", 400, 13, schoolBdd);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class EducationService.
   */
  @Test
  public void testInsert() {
    int resultInt = educationService.insert(educationTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      educationTest.setId(resultInt);
      educationService.delete(educationTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class EducationService.
   */
  @Test
  public void testUpdate() {
    int resultInt = educationService.insert(educationTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      educationTest.setId(resultInt);
      
      educationTest.setName("formation_test2");
      
      result = educationService.update(educationTest);
      
      educationService.delete(educationTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class EducationService.
   */
  @Test
  public void testDelete() {
    int resultInt = educationService.insert(educationTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      educationTest = educationService.selectById(resultInt);
      
      result = educationService.delete(educationTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class EducationService.
   */
  @Test
  public void testSelectById() {
    educationTest = educationService.selectById(1);
    boolean result = educationTest.getId() == 1;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class EducationService.
   */
  @Test
  public void testSelectAll() {
    List<Education> listEducations  = new ArrayList();
    listEducations                  = educationService.selectAll();
    boolean result                  = listEducations.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllBySchoolId method, of class EducationService.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Education> listEducations  = new ArrayList();
    listEducations                  = educationService.selectAllBySchoolId(1);
    boolean result                  = listEducations.size() > 0;
    assertTrue(result);
  }
  
}
