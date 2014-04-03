/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import metier.Administrator;
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
public class AdministratorServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School                schoolBdd;
  
  private static Administrator         administratorTest;
  private static AdministratorService  administratorService;
  
  public AdministratorServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    administratorService = new AdministratorService(); // cette ligne juste pour le coverage
    administratorService = new AdministratorService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    administratorTest = new Administrator("campus_admin_test", "campus_admin_test",
                                          "campus_admin_test@campus.com", new Date(0),
                                          "campus_admin_test", "campus_admin_test",
                                          0, "path/to/img/trombi_test",
                                          schoolBdd, null); // dernier arg : Education
                                          // null car un administrator n'a pas d'education( de formation)
    
  }
  
  @After
  public void tearDown() {
  }
  
  /**
   * Test of insert method, of class AdministratorService.
   */
  @Test
  public void testInsert() {
    int resultInt = administratorService.insert(administratorTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      administratorTest.setId(resultInt);
      administratorService.delete(administratorTest);
    }
    assertTrue(resultInt > 0);
  }
  
  
  /**
   * Test of update method, of class AdministratorService.
   */
  @Test
  public void testUpdate() {
    int resultInt = administratorService.insert(administratorTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      administratorTest.setId(resultInt);
      
      administratorTest.setLogin("campus_admin_test2");
      administratorTest.setPasswd("campus_admin_test2");
      administratorTest.setMail("campus_admin_test@campus.com");
      administratorTest.setFirstName("campus_admin_test");
      administratorTest.setLastName("campus_admin_test");
      administratorTest.setPathImgTrombi("path/to/img/trombi_test2");
      
      result = administratorService.update(administratorTest);
      
      administratorService.delete(administratorTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of delete method, of class AdministratorService.
   */
  @Test
  public void testDelete() {
      int resultInt = administratorService.insert(administratorTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      administratorTest = administratorService.selectById(resultInt);
      
      result = administratorService.delete(administratorTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class AdministratorService.
   */
  @Test
  public void testSelectById() {
    administratorTest = administratorService.selectById(1);
    boolean result    = administratorTest.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of selectByLoginPwd method, of class AdministratorService.
   */
  @Test
  public void testSelectByLoginPwd() {
    int resultInt = administratorService.insert(administratorTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      administratorTest = administratorService.selectByLoginPwd("campus_admin_test", "campus_admin_test");
      
      result = administratorTest.getLogin().equals("campus_admin_test")
                && administratorTest.getPasswd().equals("campus_admin_test");
      
      administratorService.delete(administratorTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class AdministratorService.
   */
  @Test
  public void testSelectAll() {
    List<Administrator> listAdministrators  = new ArrayList();
    listAdministrators                      = administratorService.selectAll();
    boolean result                          = listAdministrators.size() > 0;
    assertTrue(result);
  }
  
}
