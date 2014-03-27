/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
  
  private School            school;
  private Education         education;
  private List<Discipline>  listDisciplines;
  private Administrator     administrator;
  private Teacher           teacher;
  private Student           student;
  
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
    this.school = new School(1, "INSTA");
    this.education = new Education(1, "Analyste Informaticien", 200, 11, this.school, null);
    
    this.listDisciplines = new ArrayList();
    Discipline discipline1 = new Discipline(1, "Java", new Date(101), new Date(102), this.education, "Disponible");
    Discipline discipline2 = new Discipline(2, "Php", new Date(201), new Date(202), this.education, "Disponible");
    
    listDisciplines.add(discipline1);
    listDisciplines.add(discipline2);
    
    education.setDisciplines(listDisciplines);
    
    this.administrator = new Administrator(1, "campus_admin",
                                                    "campus_admin", "campus_admin@campus.com",
                                                    new Date(666), "campus_admin",
                                                    "campus_admin", 666,
                                                    school, education);
    this.teacher = new Teacher(2, "campus_teacher",
                                  "campus_teacher", "campus_teacher@campus.com",
                                  new Date(555), "campus_teacher",
                                  "campus_teacher", 555,
                                  school, education,
                                  null);
    this.student = new Student(3, "campus_student",
                                  "campus_student", "campus_student@campus.com",
                                  new Date(444), "campus_student",
                                  "campus_student", 444,
                                  school, education,
                                  null);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of toString method, of class AbstractUser.
   */
  @Test
  public void testToString() {
    boolean result = this.administrator.toString().equals("campus_admin")
                      && this.teacher.toString().equals("campus_teacher")
                      && this.student.toString().equals("campus_student");
    assertTrue(result);
  }

  /**
   * Test of getId method, of class AbstractUser.
   */
  @Test
  public void testGetId() {
    boolean result = this.administrator.getId() == 1
                      && this.teacher.getId() == 2
                      && this.student.getId() == 3;
    assertTrue(result);
  }

  /**
   * Test of getLogin method, of class AbstractUser.
   */
  @Test
  public void testGetLogin() {
    boolean result = this.administrator.getLogin().equals("campus_admin")
                      && this.teacher.getLogin().equals("campus_teacher")
                      && this.student.getLogin().equals("campus_student");
    assertTrue(result);
  }

  /**
   * Test of getPasswd method, of class AbstractUser.
   */
  @Test
  public void testGetPasswd() {
    boolean result = this.administrator.getPasswd().equals("campus_admin")
                      && this.teacher.getPasswd().equals("campus_teacher")
                      && this.student.getPasswd().equals("campus_student");
    assertTrue(result);
  }

  /**
   * Test of getMail method, of class AbstractUser.
   */
  @Test
  public void testGetMail() {
    boolean result = this.administrator.getMail().equals("campus_admin@campus.com")
                      && this.teacher.getMail().equals("campus_teacher@campus.com")
                      && this.student.getMail().equals("campus_student@campus.com");
    assertTrue(result);
  }

  /**
   * Test of getBirthDate method, of class AbstractUser.
   */
  @Test
  public void testGetBirthDate() {
    boolean result = this.administrator.getBirthDate().equals(new Date(666))
                      && this.teacher.getBirthDate().equals(new Date(555))
                      && this.student.getBirthDate().equals(new Date(444));
    assertTrue(result);
  }

  /**
   * Test of getFirstName method, of class AbstractUser.
   */
  @Test
  public void testGetFirstName() {
    boolean result = this.administrator.getFirstName().equals("campus_admin")
                      && this.teacher.getFirstName().equals("campus_teacher")
                      && this.student.getFirstName().equals("campus_student");
    assertTrue(result);
  }

  /**
   * Test of getLastName method, of class AbstractUser.
   */
  @Test
  public void testGetLastName() {
    boolean result = this.administrator.getLastName().equals("campus_admin")
                      && this.teacher.getLastName().equals("campus_teacher")
                      && this.student.getLastName().equals("campus_student");
    assertTrue(result);
  }

  /**
   * Test of getPhone method, of class AbstractUser.
   */
  @Test
  public void testGetPhone() {
    boolean result = this.administrator.getPhone() == 666
                      && this.teacher.getPhone() == 555
                      && this.student.getPhone() == 444;
    assertTrue(result);
  }

  /**
   * Test of getSchool method, of class AbstractUser.
   */
  @Test
  public void testGetSchool() {
    boolean result = this.administrator.getSchool().equals(this.school)
                      && this.teacher.getSchool().equals(this.school)
                      && this.student.getSchool().equals(this.school);
    assertTrue(result);
  }

  /**
   * Test of getEducation method, of class AbstractUser.
   */
  @Test
  public void testGetEducation() {
    boolean result = this.administrator.getEducation().equals(this.education)
                      && this.teacher.getEducation().equals(this.education)
                      && this.student.getEducation().equals(this.education);
    assertTrue(result);
  }

  /**
   * Test of setId method, of class AbstractUser.
   */
  @Test
  public void testSetId() {
    this.administrator.setId(666);
    this.teacher.setId(667);
    this.student.setId(668);
    boolean result = this.administrator.getId() == 666
                      && this.teacher.getId() == 667
                      && this.student.getId() == 668;
    assertTrue(result);
  }

  /**
   * Test of setLogin method, of class AbstractUser.
   */
  @Test
  public void testSetLogin() {
    this.administrator.setLogin("log_adm");
    this.teacher.setLogin("log_tea");
    this.student.setLogin("log_stu");
    boolean result = this.administrator.getLogin().equals("log_adm")
                      && this.teacher.getLogin().equals("log_tea")
                      && this.student.getLogin().equals("log_stu");
    assertTrue(result);
  }

  /**
   * Test of setPasswd method, of class AbstractUser.
   */
  @Test
  public void testSetPasswd() {
    this.administrator.setPasswd("pwd_adm");
    this.teacher.setPasswd("pwd_tea");
    this.student.setPasswd("pwd_stu");
    boolean result = this.administrator.getPasswd().equals("pwd_adm")
                      && this.teacher.getPasswd().equals("pwd_tea")
                      && this.student.getPasswd().equals("pwd_stu");
    assertTrue(result);
  }

  /**
   * Test of setMail method, of class AbstractUser.
   */
  @Test
  public void testSetMail() {
    this.administrator.setMail("mail_adm");
    this.teacher.setMail("mail_tea");
    this.student.setMail("mail_stu");
    boolean result = this.administrator.getMail().equals("mail_adm")
                      && this.teacher.getMail().equals("mail_tea")
                      && this.student.getMail().equals("mail_stu");
    assertTrue(result);
  }

  /**
   * Test of setBirthDate method, of class AbstractUser.
   */
  @Test
  public void testSetBirthDate() {
    this.administrator.setBirthDate(new Date(777));
    this.teacher.setBirthDate(new Date(778));
    this.student.setBirthDate(new Date(779));
    boolean result = this.administrator.getBirthDate().equals(new Date(777))
                      && this.teacher.getBirthDate().equals(new Date(778))
                      && this.student.getBirthDate().equals(new Date(779));
    assertTrue(result);
  }

  /**
   * Test of setFirstName method, of class AbstractUser.
   */
  @Test
  public void testSetFirstName() {
    this.administrator.setFirstName("fn_adm");
    this.teacher.setFirstName("fn_tea");
    this.student.setFirstName("fn_stu");
    boolean result = this.administrator.getFirstName().equals("fn_adm")
                      && this.teacher.getFirstName().equals("fn_tea")
                      && this.student.getFirstName().equals("fn_stu");
    assertTrue(result);
  }

  /**
   * Test of setLastName method, of class AbstractUser.
   */
  @Test
  public void testSetLastName() {
    this.administrator.setLastName("ln_adm");
    this.teacher.setLastName("ln_tea");
    this.student.setLastName("ln_stu");
    boolean result = this.administrator.getLastName().equals("ln_adm")
                      && this.teacher.getLastName().equals("ln_tea")
                      && this.student.getLastName().equals("ln_stu");
    assertTrue(result);
  }

  /**
   * Test of setPhone method, of class AbstractUser.
   */
  @Test
  public void testSetPhone() {
    this.administrator.setPhone(1111);
    this.teacher.setPhone(1112);
    this.student.setPhone(1113);
    boolean result = this.administrator.getPhone() == 1111
                      && this.teacher.getPhone() == 1112
                      && this.student.getPhone() == 1113;
    assertTrue(result);
  }

  /**
   * Test of setSchool method, of class AbstractUser.
   */
  @Test
  public void testSetSchool() {
    School school2 = new School(2, "INSTA2");
    
    this.administrator.setSchool(school2);
    this.teacher.setSchool(school2);
    this.student.setSchool(school2);
    boolean result = this.administrator.getSchool().equals(school2)
                      && this.teacher.getSchool().equals(school2)
                      && this.student.getSchool().equals(school2);
    assertTrue(result);
  }

  /**
   * Test of setEducation method, of class AbstractUser.
   */
  @Test
  public void testSetEducation() {
    Education education2 = new Education(2, "Analyste Informaticien Reseau", 400, 12, this.school, null);
    
    this.administrator.setEducation(education2);
    this.teacher.setEducation(education2);
    this.student.setEducation(education2);
    boolean result = this.administrator.getEducation().equals(education2)
                      && this.teacher.getEducation().equals(education2)
                      && this.student.getEducation().equals(education2);
    assertTrue(result);
  }

  public class AbstractUserImpl extends AbstractUser {

    public AbstractUserImpl() {
      super(null);
    }
  }
  
}
