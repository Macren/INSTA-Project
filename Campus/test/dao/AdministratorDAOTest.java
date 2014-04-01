/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class AdministratorDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private School            school;
  private Administrator     administrator;
  private AdministratorDAO  administratorDao;
  
  public AdministratorDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    this.school = schoolDao.selectById(1);
    
    this.administrator = new Administrator("campus_admin", "campus_admin",
                                            "campus_admin@campus.com", new Date(666),
                                            "campus_admin", "campus_admin",
                                            666, "path/to/img/trombi", this.school,
                                            null); // dernier arg : Education
                                            // null car un administrator n'a pas d'education( de formation)
    
    this.administratorDao = new AdministratorDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class AdministratorDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.administratorDao.insert(this.administrator) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class AdministratorDAO.
   */
  @Test
  public void testUpdate() {
    this.administrator = this.administratorDao.selectById(1);
    
    this.administrator.setLogin("campus_admin2");
    this.administrator.setPasswd("campus_admin2");
    this.administrator.setMail("campus_admin2@campus.com");
    this.administrator.setBirthDate(new Date(555));
    this.administrator.setFirstName("campus_admin2");
    this.administrator.setLastName("campus_admin2");
    this.administrator.setPhone(555);
    this.administrator.setPathImgTrombi("path/to/img/t");
    boolean result = this.administratorDao.update(this.administrator);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class AdministratorDAO.
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
    this.administrator.setPathImgTrombi("a/suppr");
    
    int id; // On récupère le dernier id généré
    id = this.administratorDao.insert(this.administrator);
    
    // On re-récupère l'objet, pour le suppr
    this.administrator = this.administratorDao.selectById(id);
    
    boolean result = this.administratorDao.delete(this.administrator);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class AdministratorDAO.
   */
  @Test
  public void testSelectById() {
    this.administrator = this.administratorDao.selectById(1);
    assertTrue(this.administrator.getId() == 1);
  }

  /**
   * Test of selectByLoginPwd method, of class AdministratorDAO.
   */
  @Test
  public void testSelectByLoginPwd() {
    this.administrator = this.administratorDao.selectByLoginPwd("campus_admin2", "campus_admin2");
    boolean result = this.administrator.getLogin().equals("campus_admin2")
                      && this.administrator.getPasswd().equals("campus_admin2");
    assertTrue(result);
  }

  /**
   * Test of selectAll method, of class AdministratorDAO.
   */
  @Test
  public void testSelectAll() {
    List<Administrator> listAdministrators = new ArrayList();
    listAdministrators = this.administratorDao.selectAll();
    boolean result = listAdministrators.size() > 0;
    assertTrue(result);
  }
  
}
