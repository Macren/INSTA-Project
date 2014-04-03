/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class SchoolDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School    schoolTest;
  private static SchoolDAO schoolDao;
  
  public SchoolDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class SchoolDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = schoolDao.insert(schoolTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      schoolTest.setId(resultInt);
      schoolDao.delete(schoolTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  /**
   * Test of update method, of class SchoolDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = schoolDao.insert(schoolTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      schoolTest.setId(resultInt);
      
      schoolTest.setName("ecole_test2");
      
      result = schoolDao.update(schoolTest);
      
      schoolDao.delete(schoolTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of delete method, of class SchoolDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = schoolDao.insert(schoolTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      schoolTest = schoolDao.selectById(resultInt);
      
      result = schoolDao.delete(schoolTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectById method, of class SchoolDAO.
   */
  @Test
  public void testSelectById() {
    schoolTest = schoolDao.selectById(1);
    boolean result = schoolTest.getId() == 1;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class SchoolDAO.
   */
  @Test
  public void testSelectAll() {
    List<School> listSchools  = new ArrayList();
    listSchools               = schoolDao.selectAll();
    boolean result            = listSchools.size() > 0;
    assertTrue(result);
  }
  
}
