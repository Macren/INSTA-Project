/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import metier.Administrator;
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
public class ContactDAOTest {
  
  private static final String CONNECTION_STRING_BDD_TESTS = "jdbc:mysql://localhost/campus_bdd_tests";
  
  private Administrator administrator;
  private AdministratorDAO administratorDao;
  
  private Teacher teacher;
  private TeacherDAO teacherdao;
  
  private Student student;
  private StudentDAO studentDao;
  
  private Contact contact;
  private ContactDAO contactDao;
  
  
  public ContactDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    this.administrator  = this.administratorDao.selectById(1);
    this.teacher        = this.teacherdao.selectById(2);
    this.student        = this.studentDao.selectById(3);
    
    this.contact = new Contact(this.teacher, this.student);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of insert method, of class ContactDAO.
   */
  @Test
  public void testInsert() {
    boolean result = this.contactDao.insert(this.contact) == 1;
    assertTrue(result);
  }

  /**
   * Test of update method, of class ContactDAO.
   */
  @Test
  public void testUpdate() {
    
  }

  /**
   * Test of delete method, of class ContactDAO.
   */
  @Test
  public void testDelete() {
    System.out.println("delete");
    Contact pContact = null;
    ContactDAO instance = new ContactDAO();
    boolean expResult = false;
    boolean result = instance.delete(pContact);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectById method, of class ContactDAO.
   */
  @Test
  public void testSelectById() {
    System.out.println("selectById");
    int id = 0;
    ContactDAO instance = new ContactDAO();
    Contact expResult = null;
    Contact result = instance.selectById(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of selectAll method, of class ContactDAO.
   */
  @Test
  public void testSelectAll() {
    System.out.println("selectAll");
    ContactDAO instance = new ContactDAO();
    List<Contact> expResult = null;
    List<Contact> result = instance.selectAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
  
  /**
   * Test of selectAllByOneUserId method, of class ContactDAO.
   */
  @Test
  public void testSelectAllByOneUserId() {
    System.out.println("selectAll");
    ContactDAO instance = new ContactDAO();
    List<Contact> expResult = null;
    List<Contact> result = instance.selectAll();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
