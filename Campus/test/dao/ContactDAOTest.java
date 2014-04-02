/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
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
    this.administratorDao = new AdministratorDAO(CONNECTION_STRING_BDD_TESTS);
    this.teacherdao       = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    this.studentDao       = new StudentDAO(CONNECTION_STRING_BDD_TESTS);
    
    this.contactDao       = new ContactDAO(CONNECTION_STRING_BDD_TESTS);
    
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
   * Test of delete method, of class ContactDAO.
   */
  @Test
  public void testDelete() {
    boolean result = this.contactDao.delete(this.contact);
    assertTrue(result);
  }
  
  
  
  /**
   * Test of selectAllByOneUserId method, of class ContactDAO.
   */
  @Test
  public void testSelectAllByOneUserId() {
    List<Contact> listContacts = new ArrayList();
    listContacts = this.contactDao.selectAllByOneUserId(1);
    boolean result = listContacts.size() > 0;
    assertTrue(result);
  }
  
}
