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
  
  private School                school;
  private Administrator         administrator;
  private AdministratorService  administratorService;
  
  public AdministratorServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    this.school = schoolService.selectById(1);
    
    this.administrator = new Administrator("campus_admin", "campus_admin",
                                            "campus_admin@campus.com", new Date(666),
                                            "campus_admin", "campus_admin",
                                            666, "path/to/img/trombi", this.school,
                                            null); // dernier arg : Education
                                            // null car un administrator n'a pas d'education( de formation)
    
    this.administratorService = new AdministratorService(); // cette ligne juste pour les tests
    this.administratorService = new AdministratorService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class AdministratorService.
   */
  @Test
  public void testInsert() {
    boolean result = this.administratorService.insert(this.administrator) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class AdministratorService.
   */
  @Test
  public void testUpdate() {
    this.administrator = this.administratorService.selectById(1);
    
    this.administrator.setLogin("campus_admin2");
    this.administrator.setPasswd("campus_admin2");
    this.administrator.setMail("campus_admin2@campus.com");
    this.administrator.setBirthDate(new Date(555));
    this.administrator.setFirstName("campus_admin2");
    this.administrator.setLastName("campus_admin2");
    this.administrator.setPhone(555);
    boolean result = this.administratorService.update(this.administrator);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class AdministratorService.
   */
  @Test
  public void testDelete() {
    this.administrator.setLogin("a_suppr");
    this.administrator.setPasswd("a_suppr");
    this.administrator.setMail("a_suppr@campus.com");
    this.administrator.setBirthDate(new Date(000));
    this.administrator.setFirstName("a_suppr");
    this.administrator.setLastName("a_suppr");
    this.administrator.setPhone(000);
    
    int id; // On récupère le dernier id généré
    id = this.administratorService.insert(this.administrator);
    
    // On re-récupère l'objet, pour le suppr
    this.administrator = this.administratorService.selectById(id);
    
    boolean result = this.administratorService.delete(this.administrator);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class AdministratorService.
   */
  @Test
  public void testSelectById() {
    this.administrator = this.administratorService.selectById(1);
    assertTrue(this.administrator.getId() == 1);
  }

  /**
   * Test of selectByLoginPwd method, of class AdministratorService.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.administrator = this.administratorService.selectByLoginPwd("campus_admin2", "campus_admin2");
    boolean result = this.administrator.getLogin().equals("campus_admin2")
                      && this.administrator.getPasswd().equals("campus_admin2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class AdministratorService.
   */
  @Test
  public void testSelectAll() {
    List<Administrator> listAdministrators = new ArrayList();
    listAdministrators = this.administratorService.selectAll();
    boolean result = listAdministrators.size() > 0;
    assertTrue(result);
  }
  
}
