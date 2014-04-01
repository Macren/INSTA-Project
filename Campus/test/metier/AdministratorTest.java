/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

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
public class AdministratorTest {
  
  private Administrator administrator;
  private Administrator administrator2;
  
  public AdministratorTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.administrator = new Administrator("campus_admin", "campus_admin");
    this.administrator2 = new Administrator(this.administrator);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of createStudent method, of class Administrator.
   */
  @Test
  public void testCreateStudent() {
    System.out.println("createStudent");
    Student myStudent = null;
    Administrator instance = null;
    instance.createStudent(myStudent);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of updateStudent method, of class Administrator.
   */
  @Test
  public void testUpdateStudent() {
    System.out.println("updateStudent");
    Student myStudent = null;
    Administrator instance = null;
    instance.updateStudent(myStudent);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteStudent method, of class Administrator.
   */
  @Test
  public void testDeleteStudent() {
    System.out.println("deleteStudent");
    Student myStudent = null;
    Administrator instance = null;
    instance.deleteStudent(myStudent);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of createTeacher method, of class Administrator.
   */
  @Test
  public void testCreateTeacher() {
    System.out.println("createTeacher");
    Teacher myTeacher = null;
    Administrator instance = null;
    instance.createTeacher(myTeacher);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of updateTeacher method, of class Administrator.
   */
  @Test
  public void testUpdateTeacher() {
    System.out.println("updateTeacher");
    Teacher myTeacher = null;
    Administrator instance = null;
    instance.updateTeacher(myTeacher);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteTeacher method, of class Administrator.
   */
  @Test
  public void testDeleteTeacher() {
    System.out.println("deleteTeacher");
    Teacher myTeacher = null;
    Administrator instance = null;
    instance.deleteTeacher(myTeacher);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of createEducation method, of class Administrator.
   */
  @Test
  public void testCreateEducation() {
    System.out.println("createEducation");
    Education myEducation = null;
    Administrator instance = null;
    instance.createEducation(myEducation);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of updateEducation method, of class Administrator.
   */
  @Test
  public void testUpdateEducation() {
    System.out.println("updateEducation");
    Education myEducation = null;
    Administrator instance = null;
    instance.updateEducation(myEducation);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteEducation method, of class Administrator.
   */
  @Test
  public void testDeleteEducation() {
    System.out.println("deleteEducation");
    Education myEducation = null;
    Administrator instance = null;
    instance.deleteEducation(myEducation);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of createDiscipline method, of class Administrator.
   */
  @Test
  public void testCreateDiscipline() {
    System.out.println("createDiscipline");
    Discipline myDiscipline = null;
    Administrator instance = null;
    instance.createDiscipline(myDiscipline);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of updateDiscipline method, of class Administrator.
   */
  @Test
  public void testUpdateDiscipline() {
    System.out.println("updateDiscipline");
    Discipline myDiscipline = null;
    Administrator instance = null;
    instance.updateDiscipline(myDiscipline);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteDiscipline method, of class Administrator.
   */
  @Test
  public void testDeleteDiscipline() {
    System.out.println("deleteDiscipline");
    Discipline myDiscipline = null;
    Administrator instance = null;
    instance.deleteDiscipline(myDiscipline);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of createLesson method, of class Administrator.
   */
  @Test
  public void testCreateLesson() {
    System.out.println("createLesson");
    Lesson myLesson = null;
    Administrator instance = null;
    instance.createLesson(myLesson);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of updateLesson method, of class Administrator.
   */
  @Test
  public void testUpdateLesson() {
    System.out.println("updateLesson");
    Lesson myLesson = null;
    Administrator instance = null;
    instance.updateLesson(myLesson);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of deleteLesson method, of class Administrator.
   */
  @Test
  public void testDeleteLesson() {
    System.out.println("deleteLesson");
    Lesson myLesson = null;
    Administrator instance = null;
    instance.deleteLesson(myLesson);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
