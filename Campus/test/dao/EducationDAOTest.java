/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class EducationDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School        schoolBdd;
  private static Education     educationTest;
  private static EducationDAO  educationDao;
  
  
  public EducationDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    educationDao = new EducationDAO(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class EducationDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = educationDao.insert(educationTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      educationTest.setId(resultInt);
      educationDao.delete(educationTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  /**
   * Test of update method, of class EducationDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = educationDao.insert(educationTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      educationTest.setId(resultInt);
      
      educationTest.setName("formation_test2");
      
      result = educationDao.update(educationTest);
      
      educationDao.delete(educationTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of delete method, of class EducationDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = educationDao.insert(educationTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      educationTest = educationDao.selectById(resultInt);
      
      result = educationDao.delete(educationTest);
    }
    
    assertTrue(result);
  }
  
  
  
  
  /**
   * Test of selectById method, of class EducationDAO.
   */
  @Test
  public void testSelectById() {
    educationTest = educationDao.selectById(1);
    boolean result = educationTest.getId() == 1;
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAll method, of class EducationDAO.
   */
  @Test
  public void testSelectAll() {
    List<Education> listEducations  = new ArrayList();
    listEducations                  = educationDao.selectAll();
    boolean result                  = listEducations.size() > 0;
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAllBySchoolId method, of class EducationDAO.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Education> listEducations  = new ArrayList();
    listEducations                  = educationDao.selectAllBySchoolId(1);
    boolean result                  = listEducations.size() > 0;
    assertTrue(result);
  }
  
}
