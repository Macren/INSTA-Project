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
  
  private static School         schoolTest;
  private static SchoolService  schoolService;
  
  public SchoolServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    
    schoolService = new SchoolService(); // cette ligne juste pour le coverage
    schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    schoolTest = new School("ecole_test");
    
  }
  
  @After
  public void tearDown() {
  }
  
  
  
  /**
   * Test of insert method, of class SchoolService.
   */
  @Test
  public void testInsert() {
    int resultInt = schoolService.insert(schoolTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      schoolTest.setId(resultInt);
      schoolService.delete(schoolTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  
  /**
   * Test of update method, of class SchoolService.
   */
  @Test
  public void testUpdate() {
    int resultInt = schoolService.insert(schoolTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      schoolTest.setId(resultInt);
      
      schoolTest.setName("ecole_test2");
      
      result = schoolService.update(schoolTest);
      
      schoolService.delete(schoolTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of delete method, of class SchoolService.
   */
  @Test
  public void testDelete() {
    int resultInt = schoolService.insert(schoolTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      schoolTest = schoolService.selectById(resultInt);
      
      result = schoolService.delete(schoolTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class SchoolService.
   */
  @Test
  public void testSelectById() {
    schoolTest = schoolService.selectById(1);
    boolean result = schoolTest.getId() == 1;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class SchoolService.
   */
  @Test
  public void testSelectAll() {
    List<School> listSchools  = new ArrayList();
    listSchools               = schoolService.selectAll();
    boolean result            = listSchools.size() > 0;
    assertTrue(result);
  }
  
}
