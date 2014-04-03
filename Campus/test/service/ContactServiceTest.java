/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.ArrayList;
import java.util.List;
import metier.Contact;
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
public class ContactServiceTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private static Teacher teacher;
  private static TeacherService teacherService;
  
  private static Student student;
  private static StudentService studentService;
  
  private static Contact contactTest;
  private static ContactService contactService;
  
  
  public ContactServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    teacherService        = new TeacherService(CONNECTION_STRING_BDD_TESTS);
    studentService        = new StudentService(CONNECTION_STRING_BDD_TESTS);
    
    contactService  = new ContactService(); // cette ligne juste pour le coverage
    contactService  = new ContactService(CONNECTION_STRING_BDD_TESTS);
    
    teacher        = teacherService.selectById(2);
    student        = studentService.selectById(3);
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    
    contactTest = new Contact(teacher, student);
    
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class ContactService.
   */
  @Test
  public void testInsert() {
    boolean result = contactService.insert(contactTest) == 1;
    if(result){
      contactService.delete(contactTest);
    }
    assertTrue(result);
  }
  
  
  /**
   * Test of selectByUserIds method, of class ContactService.
   */
  @Test
  public void testSelectByUserIds() {
    boolean resultBis = contactService.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      contactTest = contactService.selectByUserIds(teacher.getId(), student.getId());
      
      result = contactTest.getUtilisateur1().getId() == 2
                && contactTest.getUtilisateur2().getId() == 3;
      
      contactService.delete(contactTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAllByOneUserId method, of class ContactService.
   */
  @Test
  public void testSelectAllByOneUserId() {
    boolean resultBis = contactService.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      List<Contact> listContacts  = new ArrayList();
      listContacts                = contactService.selectAllByOneUserId(contactTest.getUtilisateur1().getId());
      
      result = listContacts.size() > 0;
      
      contactService.delete(contactTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of delete method, of class ContactService.
   */
  @Test
  public void testDelete() {
    boolean resultBis = contactService.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      
      result = contactService.delete(contactTest);
      
    }
    
    assertTrue(result);
  }
  
  
  
}
