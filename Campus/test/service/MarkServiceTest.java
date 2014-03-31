/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.ArrayList;
import java.util.List;
import metier.Discipline;
import metier.Mark;
import metier.School;
import metier.Student;
import metier.Teacher;
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
public class MarkServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private School      school;
  private Teacher     teacher;
  private Student     student;
  private Discipline  discipline;
  private Mark        mark;
  private MarkService markService;
  
  public MarkServiceTest() {
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
    
    TeacherService teacherService = new TeacherService(CONNECTION_STRING_BDD_TESTS);
    this.teacher = teacherService.selectById(2);
    
    StudentService studentDao = new StudentService(CONNECTION_STRING_BDD_TESTS);
    this.student = studentDao.selectById(3);
    
    DisciplineService disciplineService = new DisciplineService(CONNECTION_STRING_BDD_TESTS);
    this.discipline = disciplineService.selectById(1);
    
    this.mark = new Mark((float) 15.5, (float) 20, this.student, this.teacher, this.discipline, "Très bon travail!");
    
    this.markService = new MarkService(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class MarkService.
   */
  @Test
  public void testInsert() {
    boolean result = this.markService.insert(this.mark) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class MarkService.
   */
  @Test
  public void testUpdate() {
    this.mark = this.markService.selectById(1);
    
    // Ne pas oublier de lui renseigner sa Discipline (sa metiere)
    this.mark.setDiscipline(this.discipline);
    // et son Teacher
    this.mark.setTeacher(this.teacher);
    // et son Student (surtout)
    this.mark.setStudent(this.student);
    
    this.mark.setValue((float) 5.5);
    this.mark.setValueMax((float) 20);
    this.mark.setComment("Que s'est il passé ?!");
    boolean result = this.markService.update(this.mark);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class MarkService.
   */
  @Test
  public void testDelete() {
    this.mark.setValue(0);
    this.mark.setValueMax(0);
    this.mark.setComment("a_suppr");
    
    int id; // On récupère le dernier id généré
    id = this.markService.insert(this.mark);
    
    // On re-récupère l'objet, pour le suppr
    this.mark = this.markService.selectById(id);
    
    boolean result = this.markService.delete(this.mark);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class MarkService.
   */
  @Test
  public void testSelectById() {
    this.mark = this.markService.selectById(1);
    assertTrue(this.mark.getId() == 1);
  }

  /**
   * Test of selectAll method, of class MarkService.
   */
  @Test
  public void testSelectAll() {
    List<Mark> listMarks = new ArrayList();
    listMarks = this.markService.selectAll();
    boolean result = listMarks.size() > 0;
    assertTrue(result);
  }
  
}
