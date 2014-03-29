/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

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
public class MarkDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private School      school;
  private Teacher     teacher;
  private Student     student;
  private Discipline  discipline;
  private Mark        mark;
  private MarkDAO     markDao;
  
  public MarkDAOTest() {
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
    
    TeacherDAO teacherDao = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    this.teacher = teacherDao.selectById(2);
    
    StudentDAO studentDao = new StudentDAO(CONNECTION_STRING_BDD_TESTS);
    this.student = studentDao.selectById(3);
    
    DisciplineDAO disciplineDao = new DisciplineDAO(CONNECTION_STRING_BDD_TESTS);
    this.discipline = disciplineDao.selectById(1);
    
    this.mark = new Mark((float) 15.5, (float) 20, this.student, this.teacher, this.discipline, "Très bon travail!");
    
    this.markDao = new MarkDAO(CONNECTION_STRING_BDD_TESTS);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class MarkDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.markDao.insert(this.mark) > 0;
    assertTrue(result);
  }

  /**
   * Test of update method, of class MarkDAO.
   */
  @Test
  public void testUpdate() {
    this.mark = this.markDao.selectById(1);
    
    // Ne pas oublier de lui renseigner sa Discipline (sa metiere)
    this.mark.setDiscipline(this.discipline);
    // et son Teacher
    this.mark.setTeacher(this.teacher);
    // et son Student (surtout)
    this.mark.setStudent(this.student);
    
    this.mark.setValue((float) 5.5);
    this.mark.setValueMax((float) 20);
    this.mark.setComment("Que s'est il passé ?!");
    boolean result = this.markDao.update(this.mark);
    assertTrue(result);
  }

  /**
   * Test of delete method, of class MarkDAO.
   */
  @Test
  public void testDelete() {
    this.mark.setValue(0);
    this.mark.setValueMax(0);
    this.mark.setComment("a_suppr");
    
    int id; // On récupère le dernier id généré
    id = this.markDao.insert(this.mark);
    
    // On re-récupère l'objet, pour le suppr
    this.mark = this.markDao.selectById(id);
    
    boolean result = this.markDao.delete(this.mark);
    assertTrue(result);
  }

  /**
   * Test of selectById method, of class MarkDAO.
   */
  @Test
  public void testSelectById() {
    this.mark = this.markDao.selectById(1);
    assertTrue(this.mark.getId() == 1);
  }

  /**
   * Test of selectAll method, of class MarkDAO.
   */
  @Test
  public void testSelectAll() {
    List<Mark> listMarks = new ArrayList();
    listMarks = this.markDao.selectAll();
    boolean result = listMarks.size() > 0;
    assertTrue(result);
  }
  
}
