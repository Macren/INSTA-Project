/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

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
public class DisciplineServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static School        schoolBdd;
  private static Education     educationBdd;
  
  private static Discipline    disciplineTest;
  
  private static DisciplineService disciplineService;
  
  public DisciplineServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SchoolService schoolService = new SchoolService(CONNECTION_STRING_BDD_TESTS);
    schoolBdd = schoolService.selectById(1);
    
    EducationService educationService = new EducationService(CONNECTION_STRING_BDD_TESTS);
    educationBdd = educationService.selectById(1);
    
    disciplineService = new DisciplineService(); // cette ligne juste pour le coverage
    disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
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
   * Test of insert method, of class DisciplineService.
   */
  @Test
  public void testInsert() {
    int resultInt = disciplineService.insert(disciplineTest);
    // Je pense à le suppr si l'insert à fonctionné
    if(resultInt > 0){
      disciplineTest.setId(resultInt);
      disciplineService.delete(disciplineTest);
    }
    assertTrue(resultInt > 0);
  }

  /**
   * Test of update method, of class DisciplineService.
   */
  @Test
  public void testUpdate() {
    int resultInt = disciplineService.insert(disciplineTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      disciplineTest.setId(resultInt);
      
      disciplineTest.setName("matiere_test2");
      
      result = disciplineService.update(disciplineTest);
      
      disciplineService.delete(disciplineTest);
    }
    
    assertTrue(result);
  }

  /**
   * Test of delete method, of class DisciplineService.
   */
  @Test
  public void testDelete() {
    int resultInt = disciplineService.insert(disciplineTest);
    
    boolean result = false;
    
    if(resultInt > 0){
      disciplineTest = disciplineService.selectById(resultInt);
      
      result = disciplineService.delete(disciplineTest);
    }
    
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectById method, of class DisciplineService.
   */
  @Test
  public void testSelectById() {
    disciplineTest = disciplineService.selectById(1);
    boolean result = disciplineTest.getId() == 1;
    assertTrue(result);
  }
  
  
  /**
   * Test of selectByLessonId method, of class DisciplineService.
   */
  @Test
  public void testSelectByLessonId() {
    disciplineTest = disciplineService.selectByLessonId(1);
    boolean result = disciplineTest != null;
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAll method, of class DisciplineService.
   */
  @Test
  public void testSelectAll() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineService.selectAll();
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByEducationId method, of class DisciplineService.
   */
  @Test
  public void testSelectAllByEducationId() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineService.selectAllByEducationId(1);
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationIdAndEducationPromo method, of class DisciplineService.
   */
  @Test
  public void testSelectAllByEducationIdAndEducationPromo() {
    List<Discipline> listDisciplines  = new ArrayList();
    listDisciplines                   = disciplineService.selectAllByEducationIdAndEducationPromo(educationBdd);
    boolean result                    = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
}
