/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import metier.Discipline;
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
public class DisciplineDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private School        school;
  private Education     education;
  private Discipline    discipline;
  private DisciplineDAO disciplineDao;
  
  public DisciplineDAOTest() {
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
    
    EducationDAO educationDao = new EducationDAO(CONNECTION_STRING_BDD_TESTS);
    this.education = educationDao.selectById(1);
    
    this.discipline = new Discipline("Java EE", new Date(111), new Date(222), this.education, "Disponible");
    
    this.disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class DisciplineDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.disciplineDao.insert(this.discipline) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class DisciplineDAO.
   */
  @Test
  public void testUpdate() {
    this.discipline = this.disciplineDao.selectById(1);
    
    // Ne pas oublier de lui renseigner son Education (sa formation)
    this.discipline.setEducation(this.education);
    
    this.discipline.setName("Java EE 2");
    this.discipline.setBeginDate(new Date(112));
    this.discipline.setEndDate(new Date(223));
    this.discipline.setStatus("Complet");
    boolean result = this.disciplineDao.update(this.discipline);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class DisciplineDAO.
   */
  @Test
  public void testDelete() {
    this.discipline.setName("a_suppr");
    this.discipline.setBeginDate(new Date(999));
    this.discipline.setEndDate(new Date(800));
    this.discipline.setStatus("Complet");
    
    int id; // On récupère le dernier id généré
    id = this.disciplineDao.insert(this.discipline);
    
    // On re-récupère l'objet, pour le suppr
    this.discipline = this.disciplineDao.selectById(id);
    
    boolean result = this.disciplineDao.delete(this.discipline);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class DisciplineDAO.
   */
  @Test
  public void testSelectById() {
    this.discipline = this.disciplineDao.selectById(1);
    assertTrue(this.discipline.getId() == 1);
  }

  /**
   * Test of selectAll method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAll() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineDao.selectAll();
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationId method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAllByEducationId() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineDao.selectAllByEducationId(1);
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationIdAndEducationPromo method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAllByEducationIdAndEducationPromo() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineDao.selectAllByEducationIdAndEducationPromo(this.education);
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
}
