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
  
  private static School        schoolBdd;
  private static Education     educationBdd;
  
  private static Discipline    disciplineTest;
  
  private static DisciplineDAO disciplineDao;
  
  public DisciplineDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolDAO schoolDao = new SchoolDAO(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolDao.selectById(1);
    
    EducationDAO educationDao = new EducationDAO(CONNECTION_STRING_BDD_TESTS);
    educationBdd = educationDao.selectById(1);
    
    disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    disciplineTest = new Discipline("matiere_test", new Date(0), new Date(0), educationBdd, "Disponible");
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class DisciplineDAO.
   */
  @Test
  public void testInsert() {
    int resultInt = disciplineDao.insert(disciplineTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      disciplineTest.setId(resultInt);
      disciplineDao.delete(disciplineTest);
    }
    assertTrue(resultInt > 0);
  }

  /**
   * Test of update method, of class DisciplineDAO.
   */
  @Test
  public void testUpdate() {
    int resultInt = disciplineDao.insert(disciplineTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      disciplineTest.setId(resultInt);
      
      disciplineTest.setName("matiere_test2");
      
      result = disciplineDao.update(disciplineTest);
      
      disciplineDao.delete(disciplineTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of delete method, of class DisciplineDAO.
   */
  @Test
  public void testDelete() {
    int resultInt = disciplineDao.insert(disciplineTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      disciplineTest = disciplineDao.selectById(resultInt);
      
      result = disciplineDao.delete(disciplineTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class DisciplineDAO.
   */
  @Test
  public void testSelectById() {
    disciplineTest = disciplineDao.selectById(1);
    boolean result = disciplineTest.getId() == 1;
    assertTrue(result);
  }
  
  /**
   * Test of selectByLessonId method, of class DisciplineDAO.
   */
  @Test
  public void testSelectByLessonId() {
    disciplineTest = disciplineDao.selectByLessonId(1);
    boolean result = disciplineTest != null;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAll method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAll() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineDao.selectAll();
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByEducationId method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAllByEducationId() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineDao.selectAllByEducationId(1);
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationIdAndEducationPromo method, of class DisciplineDAO.
   */
  @Test
  public void testSelectAllByEducationIdAndEducationPromo() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineDao.selectAllByEducationIdAndEducationPromo(educationBdd);
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
}
