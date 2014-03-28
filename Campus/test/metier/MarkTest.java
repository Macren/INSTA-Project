/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;
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
public class MarkTest {
  
  private School      school;
  private Education   education;
  private Discipline  discipline;
  private Student     student;
  private Teacher     teacher;
  private Mark        mark;
  
  public MarkTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.school = new School(1, "INSTA");
    this.education = new Education(1, "Analyste Informaticien", 200, 11, this.school);
    this.discipline = new Discipline(1, "Java", new Date(101), new Date(102), this.education, "Disponible");
    
    this.student = new Student(3, "campus_student",
                                  "campus_student", "campus_student@campus.com",
                                  new Date(444), "campus_student",
                                  "campus_student", 444,
                                  this.school, this.education);
    this.teacher = new Teacher(2, "campus_teacher",
                                  "campus_teacher", "campus_teacher@campus.com",
                                  new Date(555), "campus_teacher",
                                  "campus_teacher", 555,
                                  this.school, this.education);
    this.mark = new Mark(1, 15, 20, this.student, this.teacher, this.discipline, "15/20 : Très bon travail !");
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class Mark.
   */
  @Test
  public void testGetId() {
    boolean result = this.mark.getId() == 1;
    assertTrue(result);
  }

  /**
   * Test of setId method, of class Mark.
   */
  @Test
  public void testSetId() {
    this.mark.setId(2);
    boolean result = this.mark.getId() == 2;
    assertTrue(result);
  }

  /**
   * Test of getValue method, of class Mark.
   */
  @Test
  public void testGetValue() {
    boolean result = this.mark.getValue() == 15;
    assertTrue(result);
  }

  /**
   * Test of setValue method, of class Mark.
   */
  @Test
  public void testSetValue() {
    this.mark.setValue((float) 15.5);
    boolean result = this.mark.getValue() == 15.5;
    assertTrue(result);
  }

  /**
   * Test of getValueMax method, of class Mark.
   */
  @Test
  public void testGetValueMax() {
    boolean result = this.mark.getValueMax() == 20;
    assertTrue(result);
  }

  /**
   * Test of setValueMax method, of class Mark.
   */
  @Test
  public void testSetValueMax() {
    this.mark.setValueMax(50);
    boolean result = this.mark.getValueMax() == 50;
    assertTrue(result);
  }

  /**
   * Test of getStudent method, of class Mark.
   */
  @Test
  public void testGetStudent() {
    boolean result = this.mark.getStudent().equals(this.student);
    assertTrue(result);
  }

  /**
   * Test of setStudent method, of class Mark.
   */
  @Test
  public void testSetStudent() {
    Student student2 = new Student(3, "campus_student2",
                                  "campus_student2", "campus_student2@campus.com",
                                  new Date(333), "campus_student2",
                                  "campus_student2", 333,
                                  this.school, this.education);
    this.mark.setStudent(student2);
    boolean result = this.mark.getStudent().equals(student2);
    assertTrue(result);
  }

  /**
   * Test of getTeacher method, of class Mark.
   */
  @Test
  public void testGetTeacher() {
    boolean result = this.mark.getTeacher().equals(this.teacher);
    assertTrue(result);
  }

  /**
   * Test of setTeacher method, of class Mark.
   */
  @Test
  public void testSetTeacher() {
    Teacher teacher2 = new Teacher(2, "campus_teacher2",
                                  "campus_teacher2", "campus_teacher2@campus.com",
                                  new Date(666), "campus_teacher2",
                                  "campus_teacher2", 666,
                                  this.school, this.education);
    this.mark.setTeacher(teacher2);
    boolean result = this.mark.getTeacher().equals(teacher2);
    assertTrue(result);
  }

  /**
   * Test of getDiscipline method, of class Mark.
   */
  @Test
  public void testGetDiscipline() {
    boolean result = this.mark.getDiscipline().equals(this.discipline);
    assertTrue(result);
  }

  /**
   * Test of setDiscipline method, of class Mark.
   */
  @Test
  public void testSetDiscipline() {
    Discipline discipline2 = new Discipline(2, "Php", new Date(901), new Date(902), this.education, "Disponible");
    
    this.mark.setDiscipline(discipline2);
    boolean result = this.mark.getDiscipline().equals(discipline2);
    assertTrue(result);
  }

  /**
   * Test of getComment method, of class Mark.
   */
  @Test
  public void testGetComment() {
    boolean result = this.mark.getComment().equals("15/20 : Très bon travail !");
    assertTrue(result);
  }

  /**
   * Test of setComment method, of class Mark.
   */
  @Test
  public void testSetComment() {
    this.mark.setComment("Cette note ne reflète pas votre travail ! Reprenez vous !");
    boolean result = this.mark.getComment().equals("Cette note ne reflète pas votre travail ! Reprenez vous !");
    assertTrue(result);
  }

  /**
   * Test of toString method, of class Mark.
   */
  @Test
  public void testToString() {
    boolean result = this.mark.toString().equals("15.0/20.0");
    assertTrue(result);
  }
  
}
