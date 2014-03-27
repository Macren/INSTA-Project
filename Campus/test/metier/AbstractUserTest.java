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
public class AbstractUserTest {
  
  public AbstractUserTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of toString method, of class AbstractUser.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getId method, of class AbstractUser.
   */
  @Test
  public void testGetId() {
    System.out.println("getId");
    AbstractUser instance = null;
    int expResult = 0;
    int result = instance.getId();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLogin method, of class AbstractUser.
   */
  @Test
  public void testGetLogin() {
    System.out.println("getLogin");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.getLogin();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPasswd method, of class AbstractUser.
   */
  @Test
  public void testGetPasswd() {
    System.out.println("getPasswd");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.getPasswd();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getMail method, of class AbstractUser.
   */
  @Test
  public void testGetMail() {
    System.out.println("getMail");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.getMail();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getBirthDate method, of class AbstractUser.
   */
  @Test
  public void testGetBirthDate() {
    System.out.println("getBirthDate");
    AbstractUser instance = null;
    Date expResult = null;
    Date result = instance.getBirthDate();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFirstName method, of class AbstractUser.
   */
  @Test
  public void testGetFirstName() {
    System.out.println("getFirstName");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.getFirstName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getLastName method, of class AbstractUser.
   */
  @Test
  public void testGetLastName() {
    System.out.println("getLastName");
    AbstractUser instance = null;
    String expResult = "";
    String result = instance.getLastName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getPhone method, of class AbstractUser.
   */
  @Test
  public void testGetPhone() {
    System.out.println("getPhone");
    AbstractUser instance = null;
    int expResult = 0;
    int result = instance.getPhone();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSchool method, of class AbstractUser.
   */
  @Test
  public void testGetSchool() {
    System.out.println("getSchool");
    AbstractUser instance = null;
    School expResult = null;
    School result = instance.getSchool();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getEducation method, of class AbstractUser.
   */
  @Test
  public void testGetEducation() {
    System.out.println("getEducation");
    AbstractUser instance = null;
    Education expResult = null;
    Education result = instance.getEducation();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setId method, of class AbstractUser.
   */
  @Test
  public void testSetId() {
    System.out.println("setId");
    int id = 0;
    AbstractUser instance = null;
    instance.setId(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLogin method, of class AbstractUser.
   */
  @Test
  public void testSetLogin() {
    System.out.println("setLogin");
    String login = "";
    AbstractUser instance = null;
    instance.setLogin(login);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPasswd method, of class AbstractUser.
   */
  @Test
  public void testSetPasswd() {
    System.out.println("setPasswd");
    String passwd = "";
    AbstractUser instance = null;
    instance.setPasswd(passwd);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setMail method, of class AbstractUser.
   */
  @Test
  public void testSetMail() {
    System.out.println("setMail");
    String mail = "";
    AbstractUser instance = null;
    instance.setMail(mail);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setBirthDate method, of class AbstractUser.
   */
  @Test
  public void testSetBirthDate() {
    System.out.println("setBirthDate");
    Date birthDate = null;
    AbstractUser instance = null;
    instance.setBirthDate(birthDate);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setFirstName method, of class AbstractUser.
   */
  @Test
  public void testSetFirstName() {
    System.out.println("setFirstName");
    String firstName = "";
    AbstractUser instance = null;
    instance.setFirstName(firstName);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setLastName method, of class AbstractUser.
   */
  @Test
  public void testSetLastName() {
    System.out.println("setLastName");
    String lastName = "";
    AbstractUser instance = null;
    instance.setLastName(lastName);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setPhone method, of class AbstractUser.
   */
  @Test
  public void testSetPhone() {
    System.out.println("setPhone");
    int phone = 0;
    AbstractUser instance = null;
    instance.setPhone(phone);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSchool method, of class AbstractUser.
   */
  @Test
  public void testSetSchool() {
    System.out.println("setSchool");
    School school = null;
    AbstractUser instance = null;
    instance.setSchool(school);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setEducation method, of class AbstractUser.
   */
  @Test
  public void testSetEducation() {
    System.out.println("setEducation");
    Education education = null;
    AbstractUser instance = null;
    instance.setEducation(education);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  public class AbstractUserImpl extends AbstractUser {

    public AbstractUserImpl() {
      super(null);
    }
  }
  
}
