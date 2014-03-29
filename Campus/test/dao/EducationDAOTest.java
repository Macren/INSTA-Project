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
  
  private School        school;
  private Education     education;
  private EducationDAO  educationDao;
  
  public EducationDAOTest() {
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
    
    this.education = new Education("Architecte Logiciel", 400, 13, this.school);
    
    this.educationDao = new EducationDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class EducationDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.educationDao.insert(this.education) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class EducationDAO.
   */
  @Test
  public void testUpdate() {
    this.education = this.educationDao.selectById(1);
    
    this.education.setName("Architecte Logiciel 2");
    this.education.setNbHours(480);
    this.education.setPromo(14);
    // on ne change pas son ecole
    boolean result = this.educationDao.update(this.education);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class EducationDAO.
   */
  @Test
  public void testDelete() {
    this.education.setName("a_suppr");
    this.education.setNbHours(0);
    this.education.setPromo(0);
    
    int id; // On récupère le dernier id généré
    id = this.educationDao.insert(this.education);
    
    // On re-récupère l'objet, pour le suppr
    this.education = this.educationDao.selectById(id);
    
    boolean result = this.educationDao.delete(this.education);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class EducationDAO.
   */
  @Test
  public void testSelectById() {
    this.education = this.educationDao.selectById(1);
    assertTrue(this.education.getId() == 1);
  }

  /**
   * Test of selectAll method, of class EducationDAO.
   */
  @Test
  public void testSelectAll() {
    List<Education> listEducations = new ArrayList();
    listEducations = this.educationDao.selectAll();
    boolean result = listEducations.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllBySchoolId method, of class EducationDAO.
   */
  @Test
  public void testSelectAllBySchoolId() {
    List<Education> listEducations = new ArrayList();
    listEducations = this.educationDao.selectAllBySchoolId(1);
    boolean result = listEducations.size() > 0;
    assertTrue(result);
  }
  
}