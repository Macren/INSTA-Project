/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Madeleine
 */
public class Teacher extends AbstractUser implements IDocUse {
    

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
     * @param pathImgTrombi
     * @param school
     * @param education
     */
    public Teacher(int id, String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, String pathImgTrombi, School school, Education education) {
        super(id, login, passwd, mail, birthDate, firstName, lastName, phone, pathImgTrombi, school, education);
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
     * @param pathImgTrombi
     * @param school
     * @param education
     */
    public Teacher(String login, String passwd, String mail, Date birthDate, String firstName, String lastName, int phone, String pathImgTrombi, School school, Education education) {
        super(login, passwd, mail, birthDate, firstName, lastName, phone, pathImgTrombi, school, education);
    }

    
    /**
     * ================
     * Copy Constructor
     * ================
     * @param other 
     */
    public Teacher(AbstractUser other) {
        super(other);
    }
    
    

    /**
     * ================================
     * Constructor for authentification
     * ================================
     * @param login
     * @param passwd 
     */
    public Teacher(String login, String passwd) {
        super(login, passwd);
    }
    
    
    
    
    /**
     * Methods
     */
    
    /**
     * boolean updateLesson()
     * ----------------------
     * The teacher update a lesson
     * 
     * @param lesson to update
     * @return true if succeed
     */
    public  boolean     updateLesson(Lesson lesson) {
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
        return true;
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
