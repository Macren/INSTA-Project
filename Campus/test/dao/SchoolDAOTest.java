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
  
  private School    school;
  private SchoolDAO schoolDao;
  
  public SchoolDAOTest() {
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
    
    this.schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class SchoolDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.schoolDao.insert(this.school) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class SchoolDAO.
   */
  @Test
  public void testUpdate() {
    this.school = this.schoolDao.selectById(1);
    
    this.school.setName("Ecole Insta 2");
    boolean result = this.schoolDao.update(this.school);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class SchoolDAO.
   */
  @Test
  public void testDelete() {
    this.school.setName("a_suppr");
    
    int id; // On récupère le dernier id généré
    id = this.schoolDao.insert(this.school);
    
    // On re-récupère l'objet, pour le suppr
    this.school = this.schoolDao.selectById(id);
    
    boolean result = this.schoolDao.delete(this.school);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class SchoolDAO.
   */
  @Test
  public void testSelectById() {
    this.school = this.schoolDao.selectById(1);
    assertTrue(this.school.getId() == 1);
  }

  /**
   * Test of selectAll method, of class SchoolDAO.
   */
  @Test
  public void testSelectAll() {
    List<School> listSchools = new ArrayList();
    listSchools = this.schoolDao.selectAll();
    boolean result = listSchools.size() > 0;
    assertTrue(result);
  }
  
}
