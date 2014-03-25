/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.util.List;

/**
 *
 * @author Madeleine
 */
public class Student extends AbstractUser implements IDocUse {
    
    /**
     * =========
     * Attribute
     * =========
     */
    private List<Lesson>    lessons;

    /**
     * ==============
     * Constructor DB
     * ==============
     * @param id
     * @param login
     * @param passwd
     * @param mail
     * @param birthDate
     * @param firstName
     * @param lastName
     * @param phone
     * @param school 
     * @param education 
     * @param lessons 
     */
    public Student(int id, String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, School school, Education education, List<Lesson> lessons) {
        super(id, login, passwd, mail, birthDate, firstName, lastName, phone, school, education);
        this.lessons = lessons;
    }

    /**
     * =================
     * Constructor no ID
     * =================
     * @param login
     * @param passwd
     * @param mail
     * @param birthDate
     * @param firstName
     * @param lastName
     * @param phone
     * @param school 
     * @param education 
     * @param lessons 
     */
    public Student(String login, String passwd, String mail, String birthDate, String firstName, String lastName, int phone, School school, Education education, List<Lesson> lessons) {
        super(login, passwd, mail, birthDate, firstName, lastName, phone, school, education);
        this.lessons = lessons;
    }

    /**
     * ===============================
     * Constructor for autentification
     * ===============================
     * @param login
     * @param passwd 
     */
    public Student(String login, String passwd) {
        super(login, passwd);
    }
    
    
    
    
    /**
     * =======
     * Methods
     * =======
     */
    
    /**
     * boolean signUpLesson()
     * ----------------------
     * The student sign up a lesson
     * 
     * @param lesson to sign up
     * @return true if succeed
     */
    public  boolean     signUpLesson(Lesson lesson) {
        return true;
    }
    
    /**
     * boolean signOutLesson()
     * -----------------------
     * The student sign out a lesson
     * 
     * @param lesson
     * @return true if succeed
     */
    public  boolean     signOutLesson(Lesson lesson) {
        return true;
    }

    
    /**
     * ==========================
     * Override Interface Methods
     * ==========================
     */
    
    /**
     * boolean uploadDoc()
     * -------------------
     * See IDocUse documentation
     * 
     * @param path
     * @return 
     */
    @Override
    public boolean uploadDoc(String path) {
        
        //TO-DO
        return false;
    }

    
    /**
     * boolean downloadDoc()
     * ---------------------
     * See IDocUse documentation
     * 
     * @param path
     * @return
     */
    @Override
    public String downloadDoc(String path) {
        
        //TO-DO
        return "";    
    }
    
    
}
