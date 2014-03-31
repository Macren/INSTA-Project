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
  
  private School            school;
  private Education         education;
  private Discipline        discipline;
  private DisciplineService disciplineService;
  
  public DisciplineServiceTest() {
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
    
    EducationService educationService = new EducationService(CONNECTION_STRING_BDD_TESTS);
    this.education = educationService.selectById(1);
    
    this.discipline = new Discipline("Java EE", new Date(111), new Date(222), this.education, "Disponible");
    
    this.disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class DisciplineService.
   */
  @Test
  public void testInsert() {
    boolean result = this.disciplineService.insert(this.discipline) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class DisciplineService.
   */
  @Test
  public void testUpdate() {
    this.discipline = this.disciplineService.selectById(1);
    
    // Ne pas oublier de lui renseigner son Education (sa formation)
    this.discipline.setEducation(this.education);
    
    this.discipline.setName("Java EE 2");
    this.discipline.setBeginDate(new Date(112));
    this.discipline.setEndDate(new Date(223));
    this.discipline.setStatus("Complet");
    boolean result = this.disciplineService.update(this.discipline);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class DisciplineService.
   */
  @Test
  public void testDelete() {
    this.discipline.setName("a_suppr");
    this.discipline.setBeginDate(new Date(999));
    this.discipline.setEndDate(new Date(800));
    this.discipline.setStatus("Complet");
    
    int id; // On récupère le dernier id généré
    id = this.disciplineService.insert(this.discipline);
    
    // On re-récupère l'objet, pour le suppr
    this.discipline = this.disciplineService.selectById(id);
    
    boolean result = this.disciplineService.delete(this.discipline);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class DisciplineService.
   */
  @Test
  public void testSelectById() {
    this.discipline = this.disciplineService.selectById(1);
    assertTrue(this.discipline.getId() == 1);
  }
  
  /**
   * Test of selectByLessonId method, of class DisciplineService.
   */
  @Test
  public void testSelectByLessonId() {
    this.discipline = this.disciplineService.selectByLessonId(1);
    assertTrue(this.discipline.getId() == 1);
  }

  /**
   * Test of selectAll method, of class DisciplineService.
   */
  @Test
  public void testSelectAll() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineService.selectAll();
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationId method, of class DisciplineService.
   */
  @Test
  public void testSelectAllByEducationId() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineService.selectAllByEducationId(1);
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }

  /**
   * Test of selectAllByEducationIdAndEducationPromo method, of class DisciplineService.
   */
  @Test
  public void testSelectAllByEducationIdAndEducationPromo() {
    List<Discipline> listDisciplines = new ArrayList();
    listDisciplines = this.disciplineService.selectAllByEducationIdAndEducationPromo(this.education);
    boolean result = listDisciplines.size() > 0;
    assertTrue(result);
  }
  
}
