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
  
  private static Teacher teacher;
  private static TeacherDAO teacherdao;
  
  private static Student student;
  private static StudentDAO studentDao;
  
  private static Contact contactTest;
  private static ContactDAO contactDao;
  
  
  public ContactDAOTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    teacherdao        = new TeacherDAO(CONNECTION_STRING_BDD_TESTS);
    studentDao        = new StudentDAO(CONNECTION_STRING_BDD_TESTS);
    
    contactDao        = new ContactDAO(CONNECTION_STRING_BDD_TESTS);
    
    teacher        = teacherdao.selectById(2);
    student        = studentDao.selectById(3);
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
   * Test of insert method, of class ContactDAO.
   */
  @Test
  public void testInsert() {
    boolean result = contactDao.insert(contactTest) == 1;
    if(result){
      contactDao.delete(contactTest);
    }
    assertTrue(result);
  }
  
  
  /**
   * Test of selectByUserIds method, of class ContactDAO.
   */
  @Test
  public void testSelectByUserIds() {
    boolean resultBis = contactDao.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      contactTest = contactDao.selectByUserIds(teacher.getId(), student.getId());
      
      result = contactTest.getUtilisateur1().getId() == 2
                && contactTest.getUtilisateur2().getId() == 3;
      
      contactDao.delete(contactTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of selectAllByOneUserId method, of class ContactDAO.
   */
  @Test
  public void testSelectAllByOneUserId() {
    boolean resultBis = contactDao.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      List<Contact> listContacts  = new ArrayList();
      listContacts                = contactDao.selectAllByOneUserId(contactTest.getUtilisateur1().getId());
      
      result = listContacts.size() > 0;
      
      contactDao.delete(contactTest);
    }
    
    assertTrue(result);
  }
  
  
  /**
   * Test of delete method, of class ContactDAO.
   */
  @Test
  public void testDelete() {
    boolean resultBis = contactDao.insert(contactTest) == 1;
    boolean result = false;
    
    if(resultBis){
      
      result = contactDao.delete(contactTest);
      
    }
    
    assertTrue(result);
  }
  
}
